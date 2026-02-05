package com.example.gitoutthere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.gitoutthere.ui.theme.GitOutThereTheme
import com.example.gitoutthere.ui.repo.RepoListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitOutThereTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    RepoListScreen()
                }
            }
        }
    }
}
