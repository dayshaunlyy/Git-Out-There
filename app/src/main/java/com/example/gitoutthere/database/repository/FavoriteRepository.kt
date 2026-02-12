package com.example.gitoutthere.database.repository

import com.example.gitoutthere.database.dao.FavoriteRepoDao
import com.example.gitoutthere.database.entities.FavoriteRepo

class FavoriteRepository(private val dao: FavoriteRepoDao) {

    suspend fun addFavorite(favorite: FavoriteRepo) {
        dao.insert(favorite)
    }

    suspend fun removeFavorite(userId: Int, repoId: Int) {
        dao.deleteByRepo(userId, repoId)
    }

    suspend fun getFavorites(userId: Int): List<FavoriteRepo> {
        return dao.getFavoritesForUser(userId)
    }
}