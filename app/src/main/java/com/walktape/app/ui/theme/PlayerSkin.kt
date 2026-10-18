package com.walktape.app.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.walktape.app.core.settings.PlayerModel

/** Value-only immutable gradient: no mutable collections exposed by skin definitions. */
@Immutable
data class SkinGradient(val start: Color, val middle: Color, val end: Color) {
    fun vertical() = Brush.verticalGradient(listOf(start, middle, end))
    fun diagonal() = Brush.linearGradient(listOf(start, middle, end))
}

@Immutable
data class PlayerSkin(
    val body: SkinGradient,
    val band: SkinGradient,
    val key: SkinGradient,
    val play: SkinGradient,
    val bodyBorder: Color,
    val bandInk: Color = WtInk,
    val keyInk: Color = WtInk,
    val playInk: Color = WtBlueNight,
    val accent: Color = WtOrange,
    val keyBorder: Color = WtSteelDark,
    val activeWell: Color = WtInk,
    val window: Color = CassetteWindowColor,
    val windowBorder: Color = WtSteelDark.copy(alpha = 0.6f),
    val shell: Color = CassetteShellColor,
    val cover: Color = WtCream,
    val coverBorder: Color = WtSteelDark,
    val labelInk: Color = WtOrangeDim,
    val titleInk: Color = WtInk,
    val artistInk: Color = WtInkSoft,
    val counterInk: Color = WtSteel,
    val statusInk: Color = WtSteelDark,
    val errorInk: Color = WtOrange,
    val bufferTrack: Color = WtSteelDark.copy(alpha = 0.4f),
    val tape: Color = TapeColor,
    val hub: Color = ReelHubColor,
    val spokes: Color = WtSteelDark,
    val bodyRadius: Dp = 20.dp,
    val windowRadius: Dp = 12.dp,
    val keyRadius: Dp = 8.dp,
    val diagonalDetail: Color = Color.Transparent,
    val protectiveRail: Color = Color.Transparent,
)

/** Player scope only: lists/forms/mini-bar/Auto keep the app's own Material theme. */
object PlayerSkins {
    val tpsL2 = PlayerSkin(
        // Body uses only start/end via bodyBrush(), preserving the original two-stop interpolation.
        body = SkinGradient(WtBlue, WtBlue, WtBlueDeep),
        band = SkinGradient(Color(0xFFE9EBEF), WtSteel, Color(0xFFA9AFBA)),
        key = SkinGradient(Color(0xFFE9EBEF), WtSteel, Color(0xFFA9AFBA)),
        play = SkinGradient(Color(0xFFF8874E), WtOrange, WtOrange),
        bodyBorder = WtSteelDark.copy(alpha = 0.7f),
    )

    fun forModel(model: PlayerModel): PlayerSkin = when (model) {
        PlayerModel.TPS_L2 -> tpsL2
        PlayerModel.WM_2 -> wm2
        PlayerModel.WM_F5 -> sports
    }

    val wm2 = tpsL2.copy(
        body = SkinGradient(Color(0xFFD9DCE2), Color(0xFFD9DCE2), Color(0xFF9DA4AE)),
        band = SkinGradient(Color(0xFF383A43), Color(0xFF252630), Color(0xFF202129)),
        key = SkinGradient(Color(0xFFEBEDF1), Color(0xFFCBD0D8), Color(0xFFAAB1BD)),
        play = SkinGradient(Color(0xFFB8DEC9), Color(0xFF8FC3A8), Color(0xFF79AE94)),
        bodyBorder = Color(0xFF656C79), bandInk = WtCream,
        playInk = Color(0xFF142D24), accent = Color(0xFF8FC3A8),
        keyBorder = Color(0xFF656C79), window = Color(0xFF202129),
        windowBorder = Color(0xFF656C79), bodyRadius = 14.dp, windowRadius = 10.dp,
        diagonalDetail = Color(0xFF53555D),
    )

    val sports = tpsL2.copy(
        body = SkinGradient(Color(0xFFF5D34D), Color(0xFFF5D34D), Color(0xFFDCAE24)),
        band = SkinGradient(Color(0xFFF5D34D), Color(0xFFEBC338), Color(0xFFDFB329)),
        key = SkinGradient(Color(0xFF383B40), Color(0xFF282B30), Color(0xFF202328)),
        play = SkinGradient(Color(0xFF59C3A5), Color(0xFF37AA8C), Color(0xFF2C977C)),
        bodyBorder = Color(0xFF564B28), bandInk = Color(0xFF202328), keyInk = WtCream,
        playInk = Color(0xFF101D1A), accent = Color(0xFF59C3A5),
        keyBorder = Color(0xFF564B28), window = Color(0xFF25282D),
        windowBorder = Color(0xFF564B28), windowRadius = 16.dp,
        protectiveRail = Color(0xFFB2891A),
    )
}

val LocalPlayerSkin = staticCompositionLocalOf { PlayerSkins.tpsL2 }

/** Body has exactly two stops, matching the original TPS-L2 brush. */
fun PlayerSkin.bodyBrush() = Brush.verticalGradient(listOf(body.start, body.end))
