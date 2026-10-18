package com.walktape.app.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Fixed materials of the TPS-L2 chassis. The device keeps its own palette in both themes;
 * text/icon pairs on these surfaces are covered by the contrast checks of wave 06.
 */
// Compatibility for app-wide text keys: these always use the default identity,
// while the native player's keys read LocalPlayerSkin. One source of material values.
val ChassisBodyBrush: Brush get() = PlayerSkins.tpsL2.bodyBrush()
val ChassisSteelBrush: Brush get() = PlayerSkins.tpsL2.band.vertical()
val KeySteelBrush: Brush get() = PlayerSkins.tpsL2.key.diagonal()
val KeyOrangeBrush: Brush get() = PlayerSkins.tpsL2.play.diagonal()

/** Cassette window surfaces: dark window, cream shell, tape and hub materials. */
val CassetteWindowColor = WtBlueNight
val CassetteShellColor = WtCream
val TapeColor = Color(0xFF3B3227)
val ReelHubColor = Color(0xFFFAFAF6)
