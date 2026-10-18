package com.walktape.app.playback

/**
 * Decision gate for external search-driven playback (MEDIA_PLAY_FROM_SEARCH /
 * prepareFromSearch, resolved through Media3's legacy bridge into the library callback).
 *
 * The playback service is exported and any installed app can send a voice-search intent;
 * the biometric app lock only gates the UI. When the lock is enabled, external search
 * must therefore be refused until the app has been unlocked once in this process
 * (fail-closed: after process death nothing plays until the user opens and unlocks
 * WalkTape). Transport controls on an already loaded queue are unaffected.
 */
object ExternalSearchGate {
    fun allowed(appLockEnabled: Boolean, appUnlockedThisProcess: Boolean): Boolean =
        !appLockEnabled || appUnlockedThisProcess
}
