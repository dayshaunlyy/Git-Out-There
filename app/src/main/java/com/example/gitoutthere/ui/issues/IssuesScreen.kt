package com.example.gitoutthere.ui.issues

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun IssuesScreen(
    owner: String,
    repo: String,
    viewModel: IssuesViewModel = viewModel()
) {
    val issues by viewModel.issues.collectAsState()

    LaunchedEffect(owner, repo) {
        viewModel.loadIssues(owner, repo)
    }

    if (issues.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {
            items(issues) { issue ->
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Text(text = issue.title)
                }
            }
        }
    }
}
