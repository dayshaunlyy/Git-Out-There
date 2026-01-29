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
class UserDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDAO

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDao = database.userDao()
    }

    @After
    fun destroy() {
        database.close()
    }

    @Test
    fun insertUserTestSuccess() = runBlocking {
        val user = User(username = "testuser", password = "password123")

        val userId = userDao.insertUser(user)

        assertTrue(userId > 0)
    }

    @Test
    fun getUserByUsername_returnsCorrectUser() = runBlocking {
        val user = User(username = "johndoe", password = "securepass")
        userDao.insertUser(user)

        val retrievedUser = userDao.getUserByUsername("johndoe")

        assertNotNull(retrievedUser)
        assertEquals("johndoe", retrievedUser?.username)
        assertEquals("securepass", retrievedUser?.password)
    }

    @Test
    fun usernameExistsTest() = runBlocking {
        val user = User(username = "existinguser", password = "pass123")
        userDao.insertUser(user)

        val exists = userDao.isUsernameExists("existinguser")

        assertTrue(exists)
    }

    @Test
    fun usernameDoesNotExistsTest() = runBlocking {
        val exists = userDao.isUsernameExists("mysteryman")

        assertFalse(exists)
    }
}