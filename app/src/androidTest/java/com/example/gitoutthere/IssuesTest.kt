package com.example.gitoutthere

import com.example.gitoutthere.api.GitHubRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException

class IssuesTest {

    @Test
    fun testGetIssues() {
        val repository = GitHubRepository()
        runBlocking {
            val issues = repository.getIssues("facebook", "react-native")
            Assert.assertNotNull(issues)
        }
    }

    @Test
    fun testGetIssues_nonExistentRepo() {
        val repository = GitHubRepository()
        runBlocking {
            try {
                repository.getIssues("nonexistentuser", "nonexistentrepo")
                Assert.fail("Expected HttpException was not thrown")
            } catch (e: HttpException) {
                Assert.assertEquals(404, e.code())
            }
        }
    }
}
