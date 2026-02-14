package com.example.gitoutthere.ui.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitoutthere.database.repository.FavoriteRepository
import com.example.gitoutthere.ui.repo.FavoritesViewModel

class FavoritesViewModelFactory(
    private val favoriteRepository: FavoriteRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(favoriteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
