package com.example.gitoutthere

import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.material3.Button
import androidx.compose.ui.tooling.preview.Preview
import com.example.gitoutthere.api.RepoViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.LaunchedEffect
import com.example.gitoutthere.ui.theme.GitOutThereTheme
import com.example.gitoutthere.ui.repo.RepoListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userId = intent.getIntExtra("USER_ID", -1)
        val isGuest = intent.getStringExtra("USER_TYPE") == "GUEST"

        intent.getStringExtra("USER_CREATED_USERNAME")?.let { username ->
            Toast.makeText(this, "$username's account successfully created", Toast.LENGTH_LONG).show()
        }

        setContent {
            GitOutThereTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    RepoListScreen(
                        isGuest = isGuest,
                        userId = userId
                    )
                }
            }
        }
    }
}
