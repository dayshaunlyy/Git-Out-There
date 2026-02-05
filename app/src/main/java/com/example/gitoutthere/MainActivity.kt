package com.example.gitoutthere

import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.material3.Button
import androidx.compose.ui.tooling.preview.Preview
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

        intent.getStringExtra("USER_CREATED_USERNAME")?.let { username ->
            Toast.makeText(this, "$username's account successfully created", Toast.LENGTH_LONG).show()
        }

        setContent {
            GitOutThereTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        onLogout = {
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    )
                    RepoScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(onLogout: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onLogout,
        modifier = modifier.testTag("logout_button")
    ){
        Text("Logout")
    }
}

@Composable
fun RepoScreen(){

    val vm : RepoViewModel = viewModel( factory = RepoViewModelFactory(GitHubRepository()))

    LaunchedEffect(Unit){
        vm.load()
    }
    Text("Repo Landing Page")
}

