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
import com.example.gitoutthere.database.AppDatabase
import com.example.gitoutthere.database.repository.SessionRepository
import com.example.gitoutthere.ui.theme.GitOutThereTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userId = intent.getIntExtra("USER_ID", -1)
        val isGuest = intent.getStringExtra("USER_TYPE") == "GUEST"

        intent.getStringExtra("USER_CREATED_USERNAME")?.let { username ->
            Toast.makeText(this, "$username's account successfully created", Toast.LENGTH_LONG).show()
        }

        val sessionRepository = SessionRepository(AppDatabase.getInstance(this).sessionDao())

        setContent {
            GitOutThereTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    RepoListScreen(
                        isGuest = isGuest,
                        onLogout = {
                            CoroutineScope(Dispatchers.IO).launch {
                                sessionRepository.clear()
                            }
                            val intent = Intent(this@MainActivity, LoginActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            finish()
                        })

                        userId = userId
                    )
                }
            }
        }
    }
}
