package com.example.gitoutthere

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gitoutthere.api.OwnerDto
import com.example.gitoutthere.api.RepoDto
import com.example.gitoutthere.ui.repo.RepoListScreen
import com.example.gitoutthere.ui.theme.GitOutThereTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepoListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun guestUser_seesGuestButton() {

        var logoutCalled = false

        composeTestRule.setContent {
            GitOutThereTheme {
                RepoListScreen(isGuest = true, userId = 1, onLogout = {logoutCalled = true})
            }
        }

        composeTestRule
            .onNodeWithTag("return_button")
            .performClick()

        assertTrue("onLogout should have been called", logoutCalled)
    }

    @Test
    fun repos_listed() {
        val repos = listOf(
            RepoDto(1, "Repo 1", "user1/Repo 1", OwnerDto("user1", ""), "Description 1", "", "Kotlin", 10),
            RepoDto(2, "Repo 2", "user2/Repo 2", OwnerDto("user2", ""), "Description 2", "", "Java", 20)
        )
        val viewModel = MockRepoViewModel(repos)

        composeTestRule.setContent {
            GitOutThereTheme {
                RepoListScreen(
                    isGuest = false,
                    userId = 1,
                    onLogout = {},
                    repoViewModel = viewModel
                )
            }
        }

        composeTestRule.onNodeWithText("Repo 1").assertExists()
        composeTestRule.onNodeWithText("Repo 2").assertExists()
    }
}