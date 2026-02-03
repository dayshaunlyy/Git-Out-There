package com.example.gitoutthere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gitoutthere.api.RepoViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.LaunchedEffect
import com.example.gitoutthere.ui.theme.GitOutThereTheme
import com.example.gitoutthere.api.RepoViewModelFactory
import com.example.gitoutthere.api.GitHubRepository


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitOutThereTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    RepoScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Main Landing Page",
        modifier = modifier
    )
}

@Composable
fun RepoScreen(){

    val vm : RepoViewModel = viewModel( factory = RepoViewModelFactory(GitHubRepository()))

    LaunchedEffect(Unit){
        vm.load()
    }
    Text("Repo Landing Page")
}

