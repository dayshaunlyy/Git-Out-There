package com.example.gitoutthere

import com.example.gitoutthere.api.RepoDto
import com.example.gitoutthere.api.RepoViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.Job

class MockRepoViewModel(repos: List<RepoDto>) : RepoViewModel() {
    override val repos = MutableStateFlow(repos)

    override fun load(): Job {
        // Do nothing
        return Job()
    }
}