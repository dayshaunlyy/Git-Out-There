package com.example.gitoutthere.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.util.Log

class RepoViewModel( private val repo: GitHubRepository = GitHubRepository()
): ViewModel() {

    fun load() = viewModelScope.launch {
        Log.d("GITHUB_API", "load() started")
        val res = repo.searchRepos("topic:android stars:>500")
        Log.d("Github", "Got ${res.items.size} repos")
    }
}