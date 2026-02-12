package com.example.gitoutthere

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gitoutthere.auth.LoginViewModel
import com.example.gitoutthere.auth.LoginViewModelFactory
import com.example.gitoutthere.database.AppDatabase
import com.example.gitoutthere.database.repository.SessionRepository
import com.example.gitoutthere.database.repository.UserRepository
import com.example.gitoutthere.ui.theme.GitOutThereTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels {
        val db = AppDatabase.getInstance(applicationContext)
        val repo = UserRepository(db.userDao())
        LoginViewModelFactory(repo)
    }

    private val sessionRepository by lazy {
        SessionRepository(AppDatabase.getInstance(this).sessionDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var showLogin by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                val session = withContext(Dispatchers.IO) { sessionRepository.getSession() }
                if (session != null) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    showLogin = true
                }
            }

            if (showLogin) {
                var error by remember { mutableStateOf<String?>(null) }

                GitOutThereTheme {
                    LoginScreen(
                        error = error,
                        onLoginClick = { username, password ->
                            viewModel.login(username, password) { ok ->
                                if (ok) {
                                    lifecycleScope.launch {
                                        withContext(Dispatchers.IO) { sessionRepository.save(username) }
                                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                        finish()
                                    }
                                } else {
                                    error = "Invalid username or password"
                                }
                            }
                        }
                    )
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    error: String?,
    onLoginClick: (String, String) -> Unit
) {
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

        if (error != null) {
            Text(
                text = error,
                modifier = Modifier.testTag("error_message")
            )
        }

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
