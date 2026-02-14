package com.example.gitoutthere.ui.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitoutthere.api.RepoDto
import com.example.gitoutthere.database.entities.FavoriteRepo
import com.example.gitoutthere.database.repository.FavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<FavoriteRepo>>(emptyList())
    val favorites: StateFlow<List<FavoriteRepo>> = _favorites.asStateFlow()

    fun loadFavorites(userId: Int) {
        viewModelScope.launch {
            _favorites.value = favoriteRepository.getFavorites(userId)
        }
    }

    fun toggleFavorite(userId: Int, repo: RepoDto) {
        viewModelScope.launch {

            val isFavorite = _favorites.value.any { it.repoId == repo.id }

            if (isFavorite) {
                favoriteRepository.removeFavorite(userId, repo.id)
            } else {
                favoriteRepository.addFavorite(
                    FavoriteRepo(
                        userId = userId,
                        repoId = repo.id,
                        name = repo.name,
                        description = repo.description,
                        url = repo.htmlUrl
                    )
                )
            }

            // Refresh list after change
            loadFavorites(userId)
        }
    }
}
