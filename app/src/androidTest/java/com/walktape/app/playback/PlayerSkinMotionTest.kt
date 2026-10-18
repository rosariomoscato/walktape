package com.walktape.app.playback

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Rect
import android.provider.Settings
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.walktape.app.MainActivity
import com.walktape.app.core.settings.PlayerModel
import com.walktape.app.ui.screens.player.CassetteWindow
import com.walktape.app.ui.theme.LocalPlayerSkin
import com.walktape.app.ui.theme.PlayerSkins
import com.walktape.app.ui.theme.WalkTapeTheme
import kotlinx.coroutines.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/** Raster test with explicit snapshot inputs: verifies animation, not audio/hardware playback. */
@RunWith(AndroidJUnit4::class)
class PlayerSkinMotionTest {
    @Test fun reelsAnimateOnlyWhenPlayingAndFreezeOnLiveReducedMotionChanges(): Unit = runBlocking {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val animator = Settings.Global.getString(context.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE)
        val transition = Settings.Global.getString(context.contentResolver, Settings.Global.TRANSITION_ANIMATION_SCALE)
        fun scale(key: String, value: String?) {
            val command = if (value == null) "settings delete global $key" else "settings put global $key $value"
            android.os.ParcelFileDescriptor.AutoCloseInputStream(instrumentation.uiAutomation.executeShellCommand(command)).use { it.readBytes() }
        }
        val activity = instrumentation.startActivitySync(Intent(context, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)) as MainActivity
        val snapshot = mutableStateOf(PlaybackSnapshot())
        val model = mutableStateOf(PlayerModel.TPS_L2)
        val bounds = java.util.concurrent.atomic.AtomicReference(Rect())
        suspend fun capture(): Bitmap {
            if (android.os.Build.VERSION.SDK_INT >= 29) {
                val committed = CompletableDeferred<Unit>()
                withContext(Dispatchers.Main) {
                    activity.window.decorView.viewTreeObserver.registerFrameCommitCallback { committed.complete(Unit) }
                    activity.window.decorView.invalidate()
                }
                withTimeout(20_000) { committed.await() }
            }
            delay(100)
            val screenshot = instrumentation.uiAutomation.takeScreenshot() ?: error("No screenshot")
            val b = bounds.get()
            assertTrue("Cassette must be on screen", b.width() > 0 && b.height() > 0 && b.bottom <= screenshot.height)
            return try { Bitmap.createBitmap(screenshot, b.left, b.top, b.width(), b.height()) }
            finally { screenshot.recycle() }
        }
        suspend fun unchanged(phase: String) {
            delay(800)
            val first = capture()
            delay(500)
            val second = capture()
            try {
                if (!first.sameAs(second)) {
                    val output = java.io.File(context.getExternalFilesDir(null), "b04-motion").apply { mkdirs() }
                    java.io.File(output, "$phase-before.png").outputStream().use { first.compress(Bitmap.CompressFormat.PNG, 100, it) }
                    java.io.File(output, "$phase-after.png").outputStream().use { second.compress(Bitmap.CompressFormat.PNG, 100, it) }
                }
                assertTrue("Reels must remain still: $phase", first.sameAs(second))
            }
            finally { first.recycle(); second.recycle() }
        }
        try {
            scale(Settings.Global.ANIMATOR_DURATION_SCALE, "1")
            scale(Settings.Global.TRANSITION_ANIMATION_SCALE, "1")
            withContext(Dispatchers.Main) {
                activity.setContent {
                    WalkTapeTheme {
                        Surface(Modifier.fillMaxSize().safeDrawingPadding()) {
                            Box(Modifier.fillMaxSize().padding(16.dp)) {
                                CompositionLocalProvider(LocalPlayerSkin provides PlayerSkins.forModel(model.value)) {
                                    CassetteWindow(snapshot.value, null, {}, Modifier.widthIn(max = 400.dp)
                                        .onGloballyPositioned { coordinates ->
                                            val b = coordinates.boundsInWindow()
                                            bounds.set(Rect(b.left.toInt(), b.top.toInt(), b.right.toInt(), b.bottom.toInt()))
                                        })
                                }
                            }
                        }
                    }
                }
            }
            withTimeout(20_000) { while (bounds.get().isEmpty) delay(100) }
            for (skin in PlayerModel.entries) {
                withContext(Dispatchers.Main) { model.value = skin; snapshot.value = PlaybackSnapshot() }
                unchanged("${skin.id}-paused")
                withContext(Dispatchers.Main) { snapshot.value = PlaybackSnapshot(isPlaying = true, playWhenReady = true) }
                delay(500)
                val before = capture()
                delay(700)
                val after = capture()
                try { assertFalse("Playing reels must change pixels for ${skin.id}", before.sameAs(after)) }
                finally { before.recycle(); after.recycle() }
                // Observer must stop the already-running frame clock while the screen remains open.
                scale(Settings.Global.ANIMATOR_DURATION_SCALE, "0")
                unchanged("${skin.id}-reduced")
                scale(Settings.Global.ANIMATOR_DURATION_SCALE, "1")
                // Playback intent alone (e.g. suppressed focus) must not rotate the reels.
                withContext(Dispatchers.Main) { snapshot.value = PlaybackSnapshot(playWhenReady = true) }
                unchanged("${skin.id}-intent-only")
            }
        } finally {
            scale(Settings.Global.ANIMATOR_DURATION_SCALE, animator)
            scale(Settings.Global.TRANSITION_ANIMATION_SCALE, transition)
            withContext(Dispatchers.Main) { activity.finish() }
        }
    }
}
