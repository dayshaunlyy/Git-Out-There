package com.example.gitoutthere.database.dao

import androidx.room.*
import com.example.gitoutthere.database.entities.FavoriteRepo

@Dao
@JvmSuppressWildcards
interface FavoriteRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteRepo): Long

    @Query("DELETE FROM favorite_repos WHERE userId = :userId AND repoId = :repoId")
    suspend fun deleteByRepo(userId: Int, repoId: Int) : Int

    @Query("SELECT * FROM favorite_repos WHERE userId = :userId")
    suspend fun getFavoritesForUser(userId: Int): List<FavoriteRepo>
}
