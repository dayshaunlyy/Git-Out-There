package com.example.gitoutthere.database.entities

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "sessions")
data class Session(

    @PrimaryKey val sessionId: Int = 1,
    val username: String

)
