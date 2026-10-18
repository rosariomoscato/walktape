package com.walktape.app.playback

import android.content.ComponentName
import android.content.Intent
import android.media.AudioManager
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.walktape.app.Graph
import com.walktape.app.core.db.ProfileSettingsEntity
import com.walktape.app.core.db.SongEntity
import com.walktape.app.core.downloads.DownloadStore
import com.walktape.app.core.db.ProfileEntity
import com.walktape.app.core.profiles.CredentialVault
import com.walktape.app.core.net.OpenSubsonicClient
import com.walktape.app.core.settings.PlayerModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

/** Real service/local audio: cosmetic emissions must not touch the musical state or audiofx. */
@RunWith(AndroidJUnit4::class)
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class PlayerSkinServiceTest {
    @Test fun changingEveryModelWhilePlayingOfflineKeepsQueueClockVolumeTimerAndEffects(): Unit = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val previous = Graph.database.profileDao().active()?.id
        val settings = Graph.settingsRepository.settings.first()
        val audioManager = context.getSystemService(android.content.Context.AUDIO_SERVICE) as AudioManager
        var profile: Long? = null
        var controller: MediaController? = null
        suspend fun await(predicate: () -> Boolean) = withTimeout(40_000) {
            while (!withContext(Dispatchers.Main) { predicate() }) delay(100)
        }
        MockWebServer().apply { start() }.use { server ->
            try {
                Graph.settingsRepository.setCrossfadeEnabled(false)
                Graph.settingsRepository.setTapeEffectEnabled(true)
                Graph.settingsRepository.setEqualizerEnabled(true)
                Graph.settingsRepository.setBassBoostEnabled(true)
                Graph.networkGate.awaitReady()
                // The previously active profile may intentionally be Solo offline (e.g. video
                // capture). Seed our own local profile instead of bypassing its network gate.
                val key = java.util.UUID.randomUUID().toString()
                val encrypted = CredentialVault().encrypt(key, "fixture-only")
                val id = Graph.database.profileDao().insert(ProfileEntity(name = "B04 local fixture",
                    serverUrl = server.url("/").toString(), username = "fixture", createdAt = 1,
                    credentialId = key, encryptedPassword = encrypted, allowLocalHttp = true))
                profile = id
                Graph.database.downloadDao().upsertSettings(ProfileSettingsEntity(id))
                Graph.profiles.activate(id)
                await { !Graph.networkGate.offlineOnly(id) }
                val bytes = TestAudioEngine.wav(TestAudioEngine.sine(960_000, 1000, 300))
                val song = SongEntity(id, "b04-local", null, "album", null, "Local PCM fixture", "Album",
                    "Fixture", null, null, 1, 1, null, null, null, 120, 128, "wav", "audio/wav",
                    bytes.size.toLong(), null, null, null, null)
                Graph.database.libraryDao().upsertSongs(listOf(song))
                server.enqueue(MockResponse.Builder().addHeader("Content-Type", "audio/wav")
                    .body(okio.Buffer().write(bytes)).build())
                Graph.downloadStore.enqueueSongs(id, listOf(song), "single")
                Graph.database.downloadDao().claimNext(id, 1)
                assertEquals(DownloadStore.Outcome.Completed, Graph.downloadStore.transfer(id, song.id,
                    OpenSubsonicClient.streamingHttpClient(), { false }) { _, _ -> })
                Graph.database.downloadDao().upsertSettings(ProfileSettingsEntity(id, offlineOnly = true))
                Graph.profiles.activate(id)
                await { Graph.networkGate.offlineOnly(id) }
                val future = withContext(Dispatchers.Main) {
                    MediaController.Builder(context, SessionToken(context,
                        ComponentName(context, PlaybackService::class.java))).buildAsync()
                }
                val c = future.get(30, TimeUnit.SECONDS); controller = c
                delay(1000)
                withContext(Dispatchers.Main) {
                    c.setMediaItems(listOf(songMediaItem(song), songMediaItem(song), songMediaItem(song)))
                    c.prepare(); c.play()
                }
                await { c.isPlaying && c.currentPosition > 1000 && c.mediaItemCount == 3 }
                withContext(Dispatchers.Main) {
                    c.shuffleModeEnabled = true
                    c.repeatMode = Player.REPEAT_MODE_ALL
                    Graph.playbackTimer.arm(SleepTimer.Mode.DURATION, 120_000)
                }
                delay(700)
                val requests = server.requestCount
                val effects = Graph.systemEffects.preferenceApplications
                val volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
                val queue = withContext(Dispatchers.Main) { (0 until c.mediaItemCount).map { c.getMediaItemAt(it).mediaId } }
                val current = withContext(Dispatchers.Main) { c.currentMediaItem!!.mediaId }
                val timerBefore = Graph.playbackTimer.state.value.remainingMs!!
                var transitions = 0
                var discontinuities = 0
                val listener = object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) { transitions++ }
                    override fun onPositionDiscontinuity(oldPosition: Player.PositionInfo, newPosition: Player.PositionInfo, reason: Int) { discontinuities++ }
                }
                withContext(Dispatchers.Main) { c.addListener(listener) }
                for (model in listOf(PlayerModel.WM_2, PlayerModel.WM_F5, PlayerModel.TPS_L2)) {
                    val position = withContext(Dispatchers.Main) { c.currentPosition }
                    Graph.settingsRepository.setPlayerModel(model)
                    await { Graph.currentSettings.playerModel == model }
                    delay(700)
                    withContext(Dispatchers.Main) {
                        assertTrue(c.isPlaying)
                        assertEquals(current, c.currentMediaItem!!.mediaId)
                        assertEquals(queue, (0 until c.mediaItemCount).map { c.getMediaItemAt(it).mediaId })
                        assertTrue(c.shuffleModeEnabled)
                        assertEquals(Player.REPEAT_MODE_ALL, c.repeatMode)
                        assertTrue("Clock must advance without a seek", c.currentPosition > position)
                    }
                    assertEquals(volume, audioManager.getStreamVolume(AudioManager.STREAM_MUSIC))
                    assertEquals(effects, Graph.systemEffects.preferenceApplications)
                    assertEquals(requests, server.requestCount)
                    assertEquals(SleepTimer.Mode.DURATION, Graph.playbackTimer.state.value.mode)
                }
                assertEquals(0, transitions)
                assertEquals(0, discontinuities)
                assertTrue(Graph.playbackTimer.state.value.remainingMs!! < timerBefore)
                withContext(Dispatchers.Main) { c.removeListener(listener); c.pause() }
                val pausedPosition = withContext(Dispatchers.Main) { c.currentPosition }
                Graph.settingsRepository.setPlayerModel(PlayerModel.WM_2)
                delay(700)
                assertFalse(withContext(Dispatchers.Main) { c.playWhenReady })
                assertEquals(pausedPosition, withContext(Dispatchers.Main) { c.currentPosition })
                assertEquals(effects, Graph.systemEffects.preferenceApplications)
            } finally {
                withContext(Dispatchers.Main) {
                    Graph.playbackTimer.cancel()
                    controller?.let { it.pause(); it.stop(); it.clearMediaItems(); it.release() }
                }
                context.stopService(Intent(context, PlaybackService::class.java))
                delay(500)
                Graph.settingsRepository.apply {
                    setPlayerModel(settings.playerModel)
                    setCrossfadeEnabled(settings.crossfadeEnabled)
                    setTapeEffectEnabled(settings.tapeEffectEnabled)
                    setEqualizerEnabled(settings.equalizerEnabled)
                    setBassBoostEnabled(settings.bassBoostEnabled)
                }
                if (previous != null) Graph.profiles.activate(previous)
                profile?.let { Graph.profiles.remove(it) }
            }
        }
    }
}
