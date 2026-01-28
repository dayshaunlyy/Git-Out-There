package com.example.gitoutthere.database.dao

import androidx.room.*
import com.example.gitoutthere.database.entities.User

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User): Int

    @Query("SELECT * FROM users WHERE username = :username") // Check for users w/ desired username
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE username = :username)")
    suspend fun isUsernameExists(username: String): Boolean

    //TODO: @Query for getting user(s)
}