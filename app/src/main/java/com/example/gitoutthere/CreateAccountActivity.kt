package com.example.gitoutthere

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gitoutthere.ui.theme.GitOutThereTheme
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import com.example.gitoutthere.auth.CreateAccountViewModel
import com.example.gitoutthere.auth.CreateAccountViewModelFactory
import com.example.gitoutthere.database.repository.UserRepository
import com.example.gitoutthere.database.AppDatabase

class CreateAccountActivity : ComponentActivity() {

    private val viewModel: CreateAccountViewModel by viewModels {
        val db = AppDatabase.getInstance(applicationContext)
        val repo = UserRepository(db.userDao())
        CreateAccountViewModelFactory(repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitOutThereTheme {
                CreateAccountScreen { username, password ->
                    viewModel.createAccount(username, password) { ok ->
                        if (ok) {
                            startActivity(Intent(this@CreateAccountActivity, MainActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CreateAccountScreen(onCreateAccountClick: (String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Create Account",
            modifier = Modifier.padding(bottom = 32.dp),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onCreateAccountClick(username, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Account")
        }

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedButton(
            onClick = { context.startActivity(Intent(context, LoginActivity::class.java)) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateAccountScreenPreview() {
    GitOutThereTheme {
        CreateAccountScreen { _, _ -> }
    }
}
