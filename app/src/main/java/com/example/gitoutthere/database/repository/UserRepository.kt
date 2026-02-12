package com.example.gitoutthere.database.repository

import com.example.gitoutthere.database.dao.UserDAO
import com.example.gitoutthere.database.entities.User

class UserRepository(private val userDAO: UserDAO) {
    suspend fun createUser(user: User): Result<Long> {
        return try {
            if (userDAO.isUsernameExists(user.username)) {
                return Result.failure(Exception("Username already exists"))
            }

            val userId = userDAO.insertUser(user)
            Result.success(userId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun authenticate(username: String, password: String): Boolean {
        val user = userDAO.getUserByUsername(username) ?: return false

        return verifyPassword(storedPassword = user.password, enteredPassword =password)
    }

    private fun verifyPassword(storedPassword : String, enteredPassword: String) : Boolean {
        return storedPassword == enteredPassword
    }

    suspend fun getUser(username: String, password: String): User? {
        return userDAO.getUser(username, password)
    }}