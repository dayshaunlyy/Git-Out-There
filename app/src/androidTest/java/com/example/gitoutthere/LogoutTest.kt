package com.example.gitoutthere

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gitoutthere.ui.repo.RepoListScreen
import com.example.gitoutthere.ui.theme.GitOutThereTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LogoutTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickingLogout_callsOnLogout() {
        var logoutCalled = false

        composeTestRule.setContent {
            GitOutThereTheme {
                RepoListScreen(
                    isGuest = false,
                    onLogout = { logoutCalled = true }
                )
            }
        }

        composeTestRule
            .onNodeWithTag("logout_button")
            .performClick()

        assertTrue("Logout callback was called", logoutCalled)
    }
}