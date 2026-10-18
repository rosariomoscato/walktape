package com.walktape.app.core.settings

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

/** Only fields actually consumed by the service's effect collector. No UI preferences. */
data class AudioEffectSettings(
    val tapeEffectEnabled: Boolean,
    val replayGainMode: ReplayGainMode,
    val equalizerEnabled: Boolean,
    val equalizerBands: String,
    val bassBoostEnabled: Boolean,
    val bassBoostStrength: Int,
)

fun Flow<AppSettings>.audioEffectSettings(): Flow<AudioEffectSettings> = filter { it.loaded }
    .map { AudioEffectSettings(it.tapeEffectEnabled, it.replayGainMode,
        it.equalizerEnabled, it.equalizerBands, it.bassBoostEnabled, it.bassBoostStrength) }
    .distinctUntilChanged()
