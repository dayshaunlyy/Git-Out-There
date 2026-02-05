package com.example.gitoutthere.ui.repo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gitoutthere.api.RepoDto
import com.example.gitoutthere.api.RepoViewModel
import com.example.gitoutthere.ui.readme.ReadmeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoListScreen(
    isGuest: Boolean,
    repoViewModel: RepoViewModel = viewModel(),
    readmeViewModel: ReadmeViewModel = viewModel()
) {
    val repos by repoViewModel.repos.collectAsState()
    var selectedRepo by remember { mutableStateOf<RepoDto?>(null) }
    val sheetState = rememberModalBottomSheetState()
    val readmeContent by readmeViewModel.readmeContent.collectAsState()

    // Trigger the load
    LaunchedEffect(Unit) {
        repoViewModel.load()
    }

    LazyColumn {
        if (isGuest) {
            item {
                Text(
                    text = "Browsing as Guest",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        items(repos) { repo ->
            RepoItem(repo = repo, onClick = { 
                selectedRepo = repo
                readmeViewModel.loadReadme(repo.owner.login, repo.name)
            })
        }
    }

    if (selectedRepo != null) {
        ModalBottomSheet(
            onDismissRequest = { selectedRepo = null },
            sheetState = sheetState,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                if (readmeContent != null) {
                    Text(text = readmeContent!!)
                } else {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun RepoItem(repo: RepoDto, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = repo.name)
            repo.description?.let {
                Text(text = it)
            }
        }
    }
}
