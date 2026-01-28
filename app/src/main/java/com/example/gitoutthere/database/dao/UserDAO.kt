package com.example.gitoutthere.database.dao

import androidx.room.*
import com.example.gitoutthere.database.entities.User

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(vararg user: User): Int

    @Query("SELECT * FROM users WHERE username = :username") // Check for users w/ desired username
    fun getUser(username: String): User?

    //TODO: @Query for getting user(s)
}