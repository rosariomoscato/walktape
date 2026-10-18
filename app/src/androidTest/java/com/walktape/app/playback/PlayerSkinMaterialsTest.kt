package com.walktape.app.playback

import android.graphics.Bitmap
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asImageBitmap
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.walktape.app.ui.theme.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/** Raster comparison of original pre-B04 brushes and extracted TPS-L2 materials. */
@RunWith(AndroidJUnit4::class)
class PlayerSkinMaterialsTest {
    private fun render(brush: Brush): Bitmap {
        val bitmap = Bitmap.createBitmap(360, 420, Bitmap.Config.ARGB_8888)
        val paint = Paint()
        brush.applyTo(Size(360f, 420f), paint, 1f)
        Canvas(bitmap.asImageBitmap()).drawRect(0f, 0f, 360f, 420f, paint)
        return bitmap
    }

    @Test fun tpsL2ExtractedGradientsArePixelIdenticalToTheBaseline() {
        val skin = PlayerSkins.tpsL2
        // Pinned pre-B04 values, independent of the production compatibility aliases.
        val steel = listOf(Color(0xFFE9EBEF), WtSteel, Color(0xFFA9AFBA))
        listOf(Brush.verticalGradient(listOf(WtBlue, WtBlueDeep)) to skin.bodyBrush(),
            Brush.verticalGradient(steel) to skin.band.vertical(),
            Brush.linearGradient(steel) to skin.key.diagonal(),
            Brush.linearGradient(listOf(Color(0xFFF8874E), WtOrange, WtOrange)) to skin.play.diagonal()).forEach { (before, after) ->
            val original = render(before); val extracted = render(after)
            try { assertTrue("TPS-L2 material raster must remain identical", original.sameAs(extracted)) }
            finally { original.recycle(); extracted.recycle() }
        }
        assertEquals(CassetteWindowColor, skin.window)
        assertEquals(CassetteShellColor, skin.shell)
        assertEquals(TapeColor, skin.tape)
        assertEquals(ReelHubColor, skin.hub)
    }
}
