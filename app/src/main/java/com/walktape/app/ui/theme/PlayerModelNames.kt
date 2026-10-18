package com.walktape.app.ui.theme

import androidx.annotation.StringRes
import com.walktape.app.R
import com.walktape.app.core.settings.PlayerModel

@StringRes
fun playerModelName(model: PlayerModel): Int = when (model) {
    PlayerModel.TPS_L2 -> R.string.player_model_tps_l2
    PlayerModel.WM_2 -> R.string.player_model_wm_2
    PlayerModel.WM_F5 -> R.string.player_model_wm_f5
}
