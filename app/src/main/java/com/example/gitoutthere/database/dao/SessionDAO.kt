package com.example.gitoutthere.database.dao

import com.example.gitoutthere.database.entities.Session
import  androidx.room.*

@Dao
@JvmSuppressWildcards
interface SessionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession( session: Session): Long

    @Query("SELECT * FROM sessions WHERE sessionId = 1")
    suspend fun getSession(): Session?

    @Query("DELETE FROM sessions WHERE sessionId = 1")
    suspend fun clear(): Int
}