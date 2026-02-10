package com.example.gitoutthere

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.gitoutthere.ui.theme.GitOutThereTheme
import androidx.activity.viewModels
import com.example.gitoutthere.auth.LoginViewModel
import com.example.gitoutthere.auth.LoginViewModelFactory
import com.example.gitoutthere.database.repository.UserRepository
import com.example.gitoutthere.database.repository.SessionRepository
import com.example.gitoutthere.database.AppDatabase
import android.widget.Toast
class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels {
        val db = AppDatabase.getInstance(applicationContext)
        val userRepo = UserRepository(db.userDao())
        val sessionRepo = SessionRepository(db.sessionDao())
        LoginViewModelFactory(userRepo, sessionRepo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitOutThereTheme {
                LoginScreen { username, password ->
                    viewModel.login(username, password) { ok ->
                        if (ok) {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(onLoginClick: (String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

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
            onClick = { onLoginClick(username, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log In")
        }

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedButton(
            onClick = { context.startActivity(Intent(context, CreateAccountActivity::class.java)) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Account")
        }
        Spacer(modifier = Modifier.height(4.dp))

        TextButton(
            onClick = {
                // Navigate as guest
                context.startActivity(
                    Intent(context, MainActivity::class.java).apply {
                        putExtra("USER_TYPE", "GUEST")
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log in as Guest")
        }
    }
}