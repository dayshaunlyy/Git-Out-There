package com.example.gitoutthere.database.entities

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,

    val username: String,
    val password: String
)