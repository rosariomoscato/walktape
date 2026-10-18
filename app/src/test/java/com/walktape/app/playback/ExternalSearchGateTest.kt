package com.walktape.app.playback

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExternalSearchGateTest {

    @Test fun `external search is allowed when the app lock is off`() {
        assertTrue(ExternalSearchGate.allowed(appLockEnabled = false, appUnlockedThisProcess = false))
    }

    @Test fun `refused while the lock is on and the app was never unlocked`() {
        assertFalse(ExternalSearchGate.allowed(appLockEnabled = true, appUnlockedThisProcess = false))
    }

    @Test fun `allowed after one unlock in this process`() {
        assertTrue(ExternalSearchGate.allowed(appLockEnabled = true, appUnlockedThisProcess = true))
    }

    @Test fun `stays allowed when the lock is later disabled`() {
        assertTrue(ExternalSearchGate.allowed(appLockEnabled = false, appUnlockedThisProcess = true))
    }
}
