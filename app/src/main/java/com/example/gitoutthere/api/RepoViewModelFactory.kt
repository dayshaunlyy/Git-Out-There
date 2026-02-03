package com.example.gitoutthere.api

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel

class RepoViewModelFactory(private val repo: GitHubRepository) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RepoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RepoViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}