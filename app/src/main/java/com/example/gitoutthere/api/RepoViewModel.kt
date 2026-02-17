package com.example.gitoutthere.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.util.Log
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.Job

open class RepoViewModel( private val repo: GitHubRepository = GitHubRepository()
): ViewModel() {

    private val _repos = MutableStateFlow<List<RepoDto>>(emptyList())
    open val repos: StateFlow<List<RepoDto>> = _repos.asStateFlow()

    open fun load(): Job = viewModelScope.launch {
        Log.d("GITHUB_API", "load() started")
        val res = repo.searchRepos("topic:android stars:>500")
        _repos.value = res.items
        Log.d("Github", "Got ${res.items.size} repos")
    }
}