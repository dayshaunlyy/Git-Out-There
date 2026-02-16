import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertTrue
import com.example.gitoutthere.ui.repo.RepoListScreen
import com.example.gitoutthere.ui.theme.GitOutThereTheme

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


}
