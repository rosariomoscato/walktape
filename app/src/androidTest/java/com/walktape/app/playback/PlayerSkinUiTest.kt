package com.walktape.app.playback

import android.content.Intent
import android.content.res.Configuration
import android.os.LocaleList
import android.view.accessibility.AccessibilityNodeInfo
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.walktape.app.Graph
import com.walktape.app.MainActivity
import com.walktape.app.core.settings.PlayerModel
import com.walktape.app.core.settings.ThemeMode
import com.walktape.app.ui.screens.player.PlayerScreen
import com.walktape.app.ui.screens.settings.PlayerModelChooser
import com.walktape.app.ui.theme.WalkTapeTheme
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.util.Locale

/** Native Compose/a11y matrix. Locale/font overrides exercise layouts, not hardware TalkBack. */
@RunWith(AndroidJUnit4::class)
class PlayerSkinUiTest {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val context = instrumentation.targetContext

    private suspend fun rendered(activity: MainActivity) {
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            val committed = CompletableDeferred<Unit>()
            withContext(Dispatchers.Main) {
                activity.window.decorView.viewTreeObserver.registerFrameCommitCallback { committed.complete(Unit) }
                activity.window.decorView.invalidate()
            }
            withTimeout(20_000) { committed.await() }
        }
        delay(100)
    }

    private fun nodes(): List<AccessibilityNodeInfo> {
        if (android.os.Build.VERSION.SDK_INT >= 33) instrumentation.uiAutomation.clearCache()
        fun flatten(node: AccessibilityNodeInfo): List<AccessibilityNodeInfo> =
            listOf(node) + (0 until node.childCount).flatMap { node.getChild(it)?.let(::flatten).orEmpty() }
        return instrumentation.uiAutomation.rootInActiveWindow?.let(::flatten).orEmpty()
    }

    private suspend fun awaitNode(predicate: (AccessibilityNodeInfo) -> Boolean): AccessibilityNodeInfo = try { withTimeout(25_000) {
        while (true) {
            nodes().firstOrNull { it.text?.toString() == "Wait" }?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            nodes().firstOrNull(predicate)?.let { return@withTimeout it }
            delay(200)
        }
        @Suppress("UNREACHABLE_CODE") error("unreachable")
    } } catch (timeout: TimeoutCancellationException) {
        throw AssertionError("Native skin node missing. Nodes: " + nodes().joinToString(" | ") {
            "${it.text}/${it.contentDescription}/checked=${it.isChecked}/selected=${it.isSelected}"
        }, timeout)
    }

    @Test fun allSkinsHaveAccessibleSelectionAndRenderInBothLanguagesThemesAndLargeFonts(): Unit = runBlocking {
        val before = Graph.settingsRepository.settings.first()
        val activity = instrumentation.startActivitySync(
            Intent(context, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)) as MainActivity
        val showChooser = mutableStateOf(true)
        val locale = mutableStateOf("it")
        val font = mutableStateOf(1f)
        val theme = mutableStateOf(ThemeMode.LIGHT)
        val selected = mutableStateOf(before.playerModel)
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        val output = File(context.getExternalFilesDir(null), "b04-ui").apply { mkdirs() }
        val viewport = InstrumentationRegistry.getArguments().getString("b04Viewport") ?: "phone"
        try {
            withContext(Dispatchers.Main) {
                activity.setContent {
                    val config = Configuration(context.resources.configuration).apply {
                        setLocales(LocaleList(Locale.forLanguageTag(locale.value)))
                        fontScale = font.value
                    }
                    val localized = context.createConfigurationContext(config)
                    CompositionLocalProvider(LocalContext provides localized, LocalConfiguration provides config,
                        LocalDensity provides Density(context.resources.displayMetrics.density, font.value)) {
                        WalkTapeTheme(theme.value) {
                            Surface(Modifier.fillMaxSize()) {
                                if (showChooser.value) {
                                    androidx.compose.foundation.layout.Column(
                                        Modifier.fillMaxSize()) {
                                        PlayerModelChooser(selected.value, true) { model ->
                                            scope.launch {
                                                Graph.settingsRepository.setPlayerModel(model)
                                                selected.value = model
                                            }
                                        }
                                    }
                                } else PlayerScreen { showChooser.value = true }
                            }
                        }
                    }
                }
            }
            // The actual radio row, not the miniature, owns one accessible selection action.
            val silver = awaitNode { it.text?.toString()?.contains("WM-2 ·") == true }
            var clickable = silver
            while (!clickable.isClickable && clickable.parent != null) clickable = clickable.parent
            assertTrue(clickable.performAction(AccessibilityNodeInfo.ACTION_CLICK))
            withTimeout(20_000) { while (Graph.settingsRepository.settings.first().playerModel != PlayerModel.WM_2) delay(100) }
            awaitNode { node ->
                if (node.text?.toString()?.contains("WM-2") != true) false
                else {
                    var choice = node
                    while (!choice.isCheckable && choice.parent != null) choice = choice.parent
                    choice.isCheckable && choice.isChecked
                }
            }

            for (language in listOf("it", "en")) for (mode in ThemeMode.entries) for (scale in listOf(1f, 2f)) {
                for (model in PlayerModel.entries) {
                    Graph.settingsRepository.setPlayerModel(model)
                    withContext(Dispatchers.Main) {
                        showChooser.value = false; locale.value = language; theme.value = mode; font.value = scale
                    }
                    awaitNode { it.text?.toString() == "WalkTape" }
                    val playLabel = if (language == "it") "Riproduci" else "Play"
                    awaitNode { it.contentDescription?.toString() == playLabel }
                    var play = awaitNode { it.viewIdResourceName == "player-play-pause" }
                    play.performAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN.id)
                    delay(200)
                    play = awaitNode { it.viewIdResourceName == "player-play-pause" }
                    val bounds = android.graphics.Rect().also(play::getBoundsInScreen)
                    assertTrue("Play target >=56dp ($viewport/${model.id}/$language/$mode/$scale): $bounds", bounds.height() >= 56 * context.resources.displayMetrics.density - 2)
                    assertTrue("Play width >=56dp: $bounds", bounds.width() >= 56 * context.resources.displayMetrics.density - 2)
                    rendered(activity)
                    val bitmap = instrumentation.uiAutomation.takeScreenshot()
                    assertNotNull(bitmap)
                    File(output, "$viewport-${model.id}-$language-${mode.name}-${scale.toInt()}x.png").outputStream().use {
                        bitmap!!.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
                    }
                    bitmap?.recycle()
                }
            }
            withContext(Dispatchers.Main) { showChooser.value = true }
            awaitNode { it.text?.toString() == "Player model" }
        } finally {
            scope.cancel()
            Graph.settingsRepository.setPlayerModel(before.playerModel)
            withContext(Dispatchers.Main) { activity.finish() }
        }
    }
}
