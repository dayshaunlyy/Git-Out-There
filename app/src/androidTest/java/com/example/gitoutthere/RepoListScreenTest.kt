import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import com.example.gitoutthere.ui.repo.RepoListScreen
import com.example.gitoutthere.ui.theme.GitOutThereTheme

class RepoListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun guestUser_seesGuestBanner() {
        composeTestRule.setContent {
            GitOutThereTheme {
                RepoListScreen(isGuest = true, userId = 1, onLogout = {})
            }
        }

        composeTestRule
            .onNodeWithTag("guest_banner")
            .assertIsDisplayed()
    }
}
