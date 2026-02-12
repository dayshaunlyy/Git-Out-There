package com.example.gitoutthere.ui.repo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gitoutthere.api.RepoDto
import com.example.gitoutthere.api.RepoViewModel
import com.example.gitoutthere.ui.issues.IssuesScreen
import com.example.gitoutthere.ui.readme.MarkdownView
import com.example.gitoutthere.ui.readme.ReadmeViewModel
import com.example.gitoutthere.database.AppDatabase
import com.example.gitoutthere.database.entities.FavoriteRepo
import com.example.gitoutthere.database.repository.FavoriteRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoListScreen(
    isGuest: Boolean,
    userId: Int,
    repoViewModel: RepoViewModel = viewModel(),
    readmeViewModel: ReadmeViewModel = viewModel()
) {
    val repos by repoViewModel.repos.collectAsState()
    var selectedRepo by remember { mutableStateOf<RepoDto?>(null) }
    val sheetState = rememberModalBottomSheetState()
    val readmeContent by readmeViewModel.readmeContent.collectAsState()
    var selectedTab by rememberSaveable { mutableStateOf(0) }
    val context = LocalContext.current
    val db = AppDatabase.getInstance(context)
    val favoriteRepoRepository = FavoriteRepository(db.favoriteRepoDao())
    var favorites by remember { mutableStateOf<List<FavoriteRepo>>(emptyList()) }
    var toggleFavoriteRequest by remember { mutableStateOf<Pair<RepoDto, Boolean>?>(null) }

    LaunchedEffect(userId) {
        repoViewModel.load()
            favorites = favoriteRepoRepository.getFavorites(userId)
        }
    }
    LaunchedEffect(toggleFavoriteRequest) {
        toggleFavoriteRequest?.let { (repo, isFavorite) ->

            if (!isGuest) {
                if (isFavorite) {
                    favoriteRepoRepository.removeFavorite(userId, repo.id.toInt())
                } else {
                    favoriteRepoRepository.addFavorite(
                        FavoriteRepo(
                            userId = userId,
                            repoId = repo.id.toInt(),
                            name = repo.name,
                            description = repo.description,
                            url = repo.htmlUrl
                        )
                    )
                }
                favorites = favoriteRepoRepository.getFavorites(userId)
            }
            toggleFavoriteRequest = null
        }
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
            val isFavorite = favorites.any { it.repoId == repo.id.toInt() }

            RepoItem(
                repo = repo,
                isFavorite = isFavorite,
                onFavoriteToggle = {
                    // trigger coroutine via LaunchedEffect
                    toggleFavoriteRequest = Pair(repo, isFavorite)
                },
                onClick = {
                    selectedRepo = repo
                    selectedTab = 0
                    readmeViewModel.loadReadme(repo.owner.login, repo.name)
                }
            )
        }
    }

    if (selectedRepo != null) {
        ModalBottomSheet(
            onDismissRequest = { selectedRepo = null },
            sheetState = sheetState,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                val tabs = listOf("README", "Issues")
                TabRow(selectedTabIndex = selectedTab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(text = title) }
                        )
                    }
                }

                when (selectedTab) {
                    0 -> {
                        val scrollState = rememberScrollState()
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .verticalScroll(scrollState)
                        ) {
                            if (readmeContent != null) {
                                MarkdownView(markdown = readmeContent!!)
                            } else {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    1 -> {
                        IssuesScreen(
                            owner = selectedRepo!!.owner.login,
                            repo = selectedRepo!!.name
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RepoItem(
    repo: RepoDto,
    isFavorite: Boolean,
    onFavoriteToggle: () -> Unit,
    onClick: () -> Unit
) {
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

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = onFavoriteToggle) {
                Text(if (isFavorite) "Unfavorite" else "Favorite")
            }
        }
    }
}
