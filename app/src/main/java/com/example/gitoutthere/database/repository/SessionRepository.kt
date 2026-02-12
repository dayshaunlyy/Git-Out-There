package com.example.gitoutthere.database.repository

import com.example.gitoutthere.database.entities.Session
import com.example.gitoutthere.database.dao.SessionDAO

class SessionRepository( private val sessionDao: SessionDAO) {

    suspend fun save( username: String) =
        sessionDao.insertSession( Session(username = username))

    suspend fun clear() = sessionDao.clear()

}