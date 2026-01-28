package com.example.gitoutthere.database.repository

import com.example.gitoutthere.database.dao.UserDAO
import com.example.gitoutthere.database.entities.User

class UserRepository(private val userDAO: UserDAO) {
    suspend fun createUser(user: User): Result<Int> {
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
}