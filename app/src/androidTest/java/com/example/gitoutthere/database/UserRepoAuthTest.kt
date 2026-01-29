package com.example.gitoutthere.database

import com.example.gitoutthere.database.dao.UserDAO
import com.example.gitoutthere.database.entities.User
import com.example.gitoutthere.database.repository.UserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class UserRepositoryAuthTest {

    @Test
    fun authenticate_returnsTrue_whenPasswordMatches() = runTest {
        val dao = mockk<UserDAO>()
        coEvery { dao.getUserByUsername("alice") } returns User(userId = 1, username = "alice", password = "pw")

        val repo = UserRepository(dao)

        val ok = repo.authenticate("alice", "pw")

        assertTrue(ok)
        coVerify(exactly = 1) { dao.getUserByUsername("alice") }
    }

    @Test
    fun authenticate_returnsFalse_whenUserNotFound() = runTest {
        val dao = mockk<UserDAO>()
        coEvery { dao.getUserByUsername("missing") } returns null

        val repo = UserRepository(dao)

        val ok = repo.authenticate("missing", "pw")

        assertFalse(ok)
        coVerify(exactly = 1) { dao.getUserByUsername("missing") }
    }

    @Test
    fun authenticate_returnsFalse_whenPasswordDoesNotMatch() = runTest {
        val dao = mockk<UserDAO>()
        coEvery { dao.getUserByUsername("alice") } returns User(userId = 1, username = "alice", password = "pw")

        val repo = UserRepository(dao)

        val ok = repo.authenticate("alice", "wrong")

        assertFalse(ok)
        coVerify(exactly = 1) { dao.getUserByUsername("alice") }
    }
}
