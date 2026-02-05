package com.example.gitoutthere.ui.readme

import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitoutthere.api.GitHubRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReadmeViewModel : ViewModel() {

    private val repository = GitHubRepository()

    private val _readmeContent = MutableStateFlow<String?>(null)
    val readmeContent: StateFlow<String?> = _readmeContent

    fun loadReadme(owner: String, repo: String) {
        viewModelScope.launch {
            try {
                val readmeDto = repository.getReadme(owner, repo)
                val decodedBytes = Base64.decode(readmeDto.content, Base64.DEFAULT)
                _readmeContent.value = String(decodedBytes, Charsets.UTF_8)
            } catch (e: Exception) {
                _readmeContent.value = "Could not load README for this repository."
            }
        }
    }
}
