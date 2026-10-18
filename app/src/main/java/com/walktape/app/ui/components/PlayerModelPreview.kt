package com.walktape.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.scale
import com.walktape.app.core.settings.PlayerModel
import com.walktape.app.ui.theme.PlayerSkins
import com.walktape.app.ui.theme.bodyBrush

/** Decorative static swatch from the real skin tokens. No music, animation or network. */
@Composable
fun PlayerModelPreview(model: PlayerModel, modifier: Modifier = Modifier) {
    val skin = PlayerSkins.forModel(model)
    Canvas(modifier) {
        scale(size.width / 100f, size.height / 106f, pivot = Offset.Zero) {
            drawRoundRect(skin.bodyBrush(), size = Size(100f, 106f), cornerRadius = CornerRadius(6f))
            drawRoundRect(skin.band.vertical(), size = Size(100f, 14f), cornerRadius = CornerRadius(3f))
            drawLine(skin.bandInk, Offset(6f, 7f), Offset(35f, 7f), 2f)
            drawRect(skin.accent, Offset(91f, 5f), Size(3f, 3f))
            drawLine(skin.diagonalDetail, Offset(75f, 0f), Offset(89f, 14f), 1f)
            drawLine(skin.protectiveRail, Offset(2f, 16f), Offset(2f, 100f), 1f)
            drawLine(skin.protectiveRail, Offset(98f, 16f), Offset(98f, 100f), 1f)
            drawRoundRect(skin.window, Offset(5f, 20f), Size(90f, 44f), CornerRadius(4f))
            drawRoundRect(skin.shell, Offset(8f, 23f), Size(84f, 36f), CornerRadius(2f))
            drawLine(skin.tape, Offset(33f, 42f), Offset(67f, 42f), 1f)
            listOf(30f, 70f).forEach { x ->
                drawCircle(skin.tape, 9f, Offset(x, 42f))
                drawCircle(skin.hub, 3.5f, Offset(x, 42f))
            }
            listOf(20f, 25f, 20f, 25f).let { widths ->
                var x = 5f
                widths.forEachIndexed { index, width ->
                    drawRoundRect(if (index == 1) skin.play.diagonal() else skin.key.diagonal(),
                        Offset(x, 70f), Size(width - 2f, 14f), CornerRadius(2f))
                    drawLine(if (index == 1) skin.playInk else skin.keyInk,
                        Offset(x + width / 2 - 3f, 77f), Offset(x + width / 2 + 1f, 77f), 2f)
                    x += width
                }
            }
            repeat(5) { index ->
                drawRoundRect(skin.key.diagonal(), Offset(5f + index * 18f, 88f), Size(16f, 13f), CornerRadius(2f))
            }
        }
    }
}
