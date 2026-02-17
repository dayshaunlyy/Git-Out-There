package com.example.gitoutthere

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gitoutthere.ui.repo.RepoListScreen
import com.example.gitoutthere.ui.theme.GitOutThereTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoritesTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun favoritesScreenRenders() {
        composeTestRule.setContent {
            GitOutThereTheme {
                RepoListScreen(isGuest = false, userId = 1, onLogout = {})
            }
        }

        composeTestRule
            .onNodeWithTag("favorites_button")
            .performClick()

        composeTestRule.onNodeWithText("View All").assertIsDisplayed()
    }

}