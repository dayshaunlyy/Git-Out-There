package com.example.gitoutthere.ui.repo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gitoutthere.api.RepoDto
import com.example.gitoutthere.api.RepoViewModel

@Composable
fun RepoListScreen(repoViewModel: RepoViewModel = viewModel()) {
    val repos by repoViewModel.repos.collectAsState()

    // Trigger the load
    repoViewModel.load()

    LazyColumn {
        items(repos) { repo ->
            RepoItem(repo = repo)
        }
    }
}

@Composable
fun RepoItem(repo: RepoDto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = repo.name)
            repo.description?.let {
                Text(text = it)
            }
        }
    }
}