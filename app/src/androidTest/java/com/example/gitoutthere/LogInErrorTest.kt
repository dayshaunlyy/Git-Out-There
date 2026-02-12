package com.example.gitoutthere

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gitoutthere.ui.theme.GitOutThereTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginErrorTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoginError() {
        composeTestRule.setContent {
            GitOutThereTheme {
                LoginScreen(
                    error = "Invalid username or password",
                    onLoginClick = { _, _ -> }
                )
            }
        }

        composeTestRule.onNodeWithTag("error_message").assertExists()
    }
}