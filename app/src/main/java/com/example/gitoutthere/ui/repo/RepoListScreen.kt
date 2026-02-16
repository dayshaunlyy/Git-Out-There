package com.example.gitoutthere.ui.repo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gitoutthere.api.RepoDto
import com.example.gitoutthere.api.RepoViewModel
import com.example.gitoutthere.ui.issues.IssuesScreen
import com.example.gitoutthere.ui.readme.MarkdownView
import com.example.gitoutthere.ui.readme.ReadmeViewModel
import com.example.gitoutthere.database.AppDatabase
import com.example.gitoutthere.database.entities.FavoriteRepo
import com.example.gitoutthere.database.repository.FavoriteRepository
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoListScreen(
    isGuest: Boolean,
    userId: Int,
    onLogout: () -> Unit,
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
    val favoriteRepository = FavoriteRepository(db.favoriteRepoDao())
    val favoritesViewModel: FavoritesViewModel = viewModel(factory = FavoritesViewModelFactory(favoriteRepository))
    val favorites by favoritesViewModel.favorites.collectAsState()
    var showFavorites by remember { mutableStateOf(false) }


    LaunchedEffect(userId) {
        repoViewModel.load()

        if (!isGuest) {
            favoritesViewModel.loadFavorites(userId)
        }
    }



    Column {
        if (!isGuest) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = onLogout,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp)
                        .testTag("logout_button")
                ) {
                    Text("Log Out")
                }
                Button(
                    onClick = { showFavorites = !showFavorites },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)
                ) {
                    Text(if (showFavorites) "View All" else "View Favorites")
                }
            }
        }

        if (showFavorites) {
            FavoritesScreen(userId = userId)
        } else {
            LazyColumn {
                if (isGuest) {
                    item{
                        Button (
                            onClick = onLogout,
                            modifier = Modifier
                                .fillMaxWidth( )
                                .padding(start = 8.dp)
                                .testTag("return_button")
                        ){
                            Text("Return")
                        }
                    }
                }

                items(repos) { repo ->
                    val isFavorite = favorites.any { it.repoId == repo.id}

                    RepoItem(
                        repo = repo,
                        isFavorite = isFavorite,
                        isGuest = isGuest,
                        onFavoriteToggle = {
                            favoritesViewModel.toggleFavorite(userId, repo)
                        },
                        onClick = {
                            selectedRepo = repo
                            selectedTab = 0
                            readmeViewModel.loadReadme(repo.owner.login, repo.name)
                        }
                    )
                }
            }
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
    isGuest: Boolean,
    onFavoriteToggle: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(Modifier.weight(1f)) {
                Text(text = repo.name)
                repo.description?.let {
                    Text(text = it)
                }
            }

            if (!isGuest) {
                IconButton(onClick = onFavoriteToggle) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Favorite",
                        tint = if (isFavorite)
                            //on toggle gold
                            Color(0xFFFFC107)
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun FavoritesScreen(
    userId: Int
) {
    val context = LocalContext.current
    val db = AppDatabase.getInstance(context)
    val favoriteRepository = FavoriteRepository(db.favoriteRepoDao())

    val favoritesViewModel: FavoritesViewModel = viewModel(
        factory = FavoritesViewModelFactory(favoriteRepository)
    )

    val favorites by favoritesViewModel.favorites.collectAsState()

    LaunchedEffect(userId) {
        favoritesViewModel.loadFavorites(userId)
    }

    LazyColumn {
        items(favorites) { favorite ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = favorite.name)
                    favorite.description?.let {
                        Text(text = it)
                    }
                }
            }
        }
    }
}
