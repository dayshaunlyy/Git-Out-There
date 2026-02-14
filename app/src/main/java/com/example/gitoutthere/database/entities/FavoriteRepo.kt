package com.example.gitoutthere.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_repos")
data class FavoriteRepo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val repoId: Long,
    val name: String,
    val description: String?,
    val url: String
)