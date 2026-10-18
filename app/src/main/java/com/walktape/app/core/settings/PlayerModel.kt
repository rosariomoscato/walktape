package com.walktape.app.core.settings

/** Device-only visual identity. Persist explicit IDs, never names/ordinals. */
enum class PlayerModel(val id: String) {
    TPS_L2("tps-l2"), WM_2("wm-2"), WM_F5("wm-f5");

    companion object {
        fun fromId(id: String?): PlayerModel = entries.firstOrNull { it.id == id } ?: TPS_L2
    }
}
