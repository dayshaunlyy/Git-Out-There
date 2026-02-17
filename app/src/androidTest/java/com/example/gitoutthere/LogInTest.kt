package com.example.gitoutthere

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gitoutthere.ui.repo.RepoListScreen
import com.example.gitoutthere.ui.theme.GitOutThereTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LogInTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun successfulLogin_navigatesToRepoListScreen() {
        var isLoggedIn by mutableStateOf(false)

        composeTestRule.setContent {
            GitOutThereTheme {
                if (isLoggedIn) {
                    RepoListScreen(isGuest = false, userId = 1, onLogout = {})
                } else {
                    LoginScreen(error = null, onLoginClick = { _, _ -> isLoggedIn = true })
                }
            }
        }

        composeTestRule.onNodeWithTag("username_field").performTextInput("test")
        composeTestRule.onNodeWithTag("password_field").performTextInput("test")
        composeTestRule.onNodeWithTag("login_button").performClick()

        composeTestRule.onNodeWithTag("logout_button").assertIsDisplayed()
    }
}