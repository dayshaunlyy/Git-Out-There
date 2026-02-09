package com.example.gitoutthere

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.gitoutthere.ui.readme.MarkdownView
import com.example.gitoutthere.ui.theme.GitOutThereTheme
import org.junit.Rule
import org.junit.Test

class ReadMeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun markdownView_rendersMarkdown() {
        val markdown = "## Hello World"
        composeTestRule.setContent {
            GitOutThereTheme {
                MarkdownView(markdown = markdown)
            }
        }

        // Assert that the markdown is rendered correctly
        composeTestRule
            .onNodeWithText("Hello World")
            .assertIsDisplayed()
    }
}
