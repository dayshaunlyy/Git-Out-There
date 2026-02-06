package com.example.gitoutthere.ui.issues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitoutthere.api.GitHubRepository
import com.example.gitoutthere.api.IssueDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IssuesViewModel(private val repository: GitHubRepository = GitHubRepository()) : ViewModel() {

    private val _issues = MutableStateFlow<List<IssueDto>>(emptyList())
    val issues: StateFlow<List<IssueDto>> = _issues

    fun loadIssues(owner: String, repo: String) {
        viewModelScope.launch {
            try {
                _issues.value = repository.getIssues(owner, repo)
            } catch (e: Exception) {
                _issues.value = emptyList()
            }
        }
    }
}
