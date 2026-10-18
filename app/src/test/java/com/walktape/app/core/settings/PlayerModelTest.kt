package com.walktape.app.core.settings

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class PlayerModelTest {
    @Test fun missingAndUnknownIdsUseTheDefault() {
        assertEquals(PlayerModel.TPS_L2, AppSettings().playerModel)
        listOf(null, "", "WM_2", "1", "future-model").forEach {
            assertEquals(PlayerModel.TPS_L2, PlayerModel.fromId(it))
        }
        assertEquals(PlayerModel.WM_2, PlayerModel.fromId("wm-2"))
        assertEquals(PlayerModel.WM_F5, PlayerModel.fromId("wm-f5"))
    }

    @Test fun cosmeticChangesNeverEmitEffectUpdatesButAudioChangesDo(): Unit = runBlocking {
        val original = AppSettings(loaded = true, equalizerEnabled = true, equalizerBands = "0:200")
        val emissions = listOf(AppSettings(), original,
            original.copy(playerModel = PlayerModel.WM_2),
            original.copy(playerModel = PlayerModel.WM_F5, themeMode = ThemeMode.DARK),
            original.copy(hapticsEnabled = false, updateLastCheckAt = 1234),
            original.copy(bassBoostEnabled = true),
            original.copy(bassBoostEnabled = true, bassBoostStrength = 700),
            original.copy(tapeEffectEnabled = true),
            original.copy(replayGainMode = ReplayGainMode.TRACK),
            original.copy(equalizerBands = "0:400"),
            original.copy(equalizerEnabled = false))
            .asFlow().audioEffectSettings().toList()
        assertEquals(7, emissions.size)
        assertEquals("0:200", emissions.first().equalizerBands)
        assertFalse(emissions.last().equalizerEnabled)
    }
}
