package com.example.gitoutthere

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gitoutthere.database.AppDatabase
import com.example.gitoutthere.database.entities.User
import com.example.gitoutthere.database.dao.UserDAO
import com.example.gitoutthere.database.repository.UserRepository
import kotlin.Result

import kotlinx.coroutines.runBlocking // blocks current thread until coroutine finishes
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class UserRepoTest {

    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDAO
    private lateinit var userRepository: UserRepository


    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDao = database.userDao()
        userRepository = UserRepository(userDao)
    }

    @After
    fun destroy() {
        database.close()
    }

    @Test
    fun createUserTestSuccess() = runBlocking {
        val user = User(username = "testuser", password = "password123")

        val result = userRepository.createUser(user)

        assertTrue("Result should be success", result.isSuccess)
    }

    @Test
    fun createUserTestDuplicateUsername() = runBlocking {
        val user1 = User(username = "testuser", password = "password123")
        val user2 = User(username = "testuser", password = "password456")
        userRepository.createUser(user1)


        val result = userRepository.createUser(user2)

        assertTrue("Result should be failure", result.isFailure)
    }
}