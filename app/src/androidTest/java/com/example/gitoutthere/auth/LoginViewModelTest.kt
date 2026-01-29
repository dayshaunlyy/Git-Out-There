package com.example.gitoutthere.auth

import com.example.gitoutthere.database.repository.UserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    //  dispatcher lets test control when coroutines finish
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {

        Dispatchers.setMain(dispatcher)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun login_returnsTrue_whenRepoAuthenticates() = runTest {
        val repo = mockk<UserRepository>()
        coEvery { repo.authenticate("alice", "pw") } returns true

        val vm = LoginViewModel(repo)

        var result: Boolean? = null
        vm.login("alice", "pw") { ok -> result = ok }


        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(true, result)
        coVerify(exactly = 1) { repo.authenticate("alice", "pw") }
    }

    @Test
    fun login_returnsFalse_whenRepoRejects() = runTest {
        val repo = mockk<UserRepository>()
        coEvery { repo.authenticate("alice", "wrong") } returns false

        val vm = LoginViewModel(repo)

        var result: Boolean? = null
        vm.login("alice", "wrong") { ok -> result = ok }

        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(false, result)
        coVerify(exactly = 1) { repo.authenticate("alice", "wrong") }
    }

    @Test
    fun login_trimsUsername_beforeCallingRepo() = runTest {
        val repo = mockk<UserRepository>()
        coEvery { repo.authenticate("alice", "pw") } returns true

        val vm = LoginViewModel(repo)

        var result: Boolean? = null
        vm.login("  alice  ", "pw") { ok -> result = ok }

        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(true, result)
        coVerify(exactly = 1) { repo.authenticate("alice", "pw") } // verifies trimming happened
    }
}
