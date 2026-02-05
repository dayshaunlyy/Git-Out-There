package com.example.gitoutthere.ui.readme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitoutthere.api.GitHubRepository
import com.example.gitoutthere.api.ReadmeDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReadmeViewModel : ViewModel() {

    private val repository = GitHubRepository()

    private val _readme = MutableStateFlow<ReadmeDto?>(null)
    val readme: StateFlow<ReadmeDto?> = _readme

    fun loadReadme(owner: String, repo: String) {
        viewModelScope.launch {
            val readme = repository.getReadme(owner, repo)
            _readme.value = readme
        }
    }
}