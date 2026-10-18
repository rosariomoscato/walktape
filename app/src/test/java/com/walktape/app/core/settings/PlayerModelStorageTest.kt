package com.walktape.app.core.settings

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class PlayerModelStorageTest {
    @get:Rule val temporary = TemporaryFolder()

    @Test fun modelSurvivesAStoreReopenAndUnknownIdsDoNotDamageOtherPreferences(): Unit = runBlocking {
        val file = java.io.File(temporary.root, "device.preferences_pb")
        suspend fun session(block: suspend (SettingsRepository, androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences>) -> Unit) {
            val job = SupervisorJob()
            val store = PreferenceDataStoreFactory.create(scope = CoroutineScope(Dispatchers.IO + job), produceFile = { file })
            try { block(SettingsRepository(store), store) } finally { job.cancelAndJoin() }
        }
        session { repo, _ ->
            assertEquals(PlayerModel.TPS_L2, repo.settings.first().playerModel)
            repo.setThemeMode(ThemeMode.DARK)
            repo.setTapeEffectEnabled(true)
            repo.setPlayerModel(PlayerModel.WM_2)
        }
        session { repo, store ->
            val restored = repo.settings.first()
            assertEquals(PlayerModel.WM_2, restored.playerModel)
            assertTrue(restored.loaded && restored.tapeEffectEnabled)
            store.edit { it[stringPreferencesKey("player_model")] = "unknown-from-a-future-build" }
            assertEquals(PlayerModel.TPS_L2, repo.settings.first().playerModel)
            assertEquals(ThemeMode.DARK, repo.settings.first().themeMode)
            repo.setPlayerModel(PlayerModel.WM_F5)
        }
        session { repo, _ -> assertEquals(PlayerModel.WM_F5, repo.settings.first().playerModel) }
    }
}
