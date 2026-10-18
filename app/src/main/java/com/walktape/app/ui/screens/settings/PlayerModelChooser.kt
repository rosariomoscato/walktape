package com.walktape.app.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.walktape.app.R
import com.walktape.app.core.settings.PlayerModel
import com.walktape.app.ui.components.PlayerModelPreview
import com.walktape.app.ui.theme.playerModelName

@Composable
internal fun PlayerModelChooser(selected: PlayerModel, enabled: Boolean, onSelect: (PlayerModel) -> Unit) {
    Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp).selectableGroup()) {
        Text(stringResource(R.string.settings_player_model), style = MaterialTheme.typography.bodyMedium)
        Text(stringResource(R.string.settings_player_model_help),
            style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        PlayerModel.entries.forEach { model ->
            Row(Modifier.fillMaxWidth().heightIn(min = 80.dp)
                .selectable(selected == model, enabled = enabled, role = Role.RadioButton,
                    onClick = { onSelect(model) }).padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically) {
                PlayerModelPreview(model, Modifier.size(72.dp, 76.dp))
                Text(stringResource(playerModelName(model)), style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f))
                RadioButton(selected = selected == model, enabled = enabled, onClick = null)
            }
        }
    }
}
