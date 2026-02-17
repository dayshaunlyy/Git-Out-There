package com.example.gitoutthere

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.viewModels
import com.example.gitoutthere.auth.LoginViewModel
import com.example.gitoutthere.auth.LoginViewModelFactory
import com.example.gitoutthere.database.AppDatabase
import com.example.gitoutthere.database.repository.UserRepository
import com.example.gitoutthere.ui.theme.GitOutThereTheme

class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels {
        val db = AppDatabase.getInstance(applicationContext)
        val repo = UserRepository(db.userDao())
        LoginViewModelFactory(repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var error by remember { mutableStateOf<String?>(null) }

            GitOutThereTheme {
                LoginScreen(
                    error = error,
                    onLoginClick = { username, password ->
                        viewModel.login(username, password) { user ->
                            if (user != null) {
                                val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                                    putExtra("USER_ID", user.userId)
                                }
                                startActivity(intent)
                                finish()
                            } else {
                                error = "Invalid username or password"
                            }
                        }
                    }
                )
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Text(
                    text = "Git Out There",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 48.sp
                )
                Text(
                    text = "Find open-source projects that need you!",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }


        Spacer(modifier = Modifier.height(128.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth().testTag("username_field")
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().testTag("password_field")
        )

        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.testTag("error_message")
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onLoginClick(username, password) },
            modifier = Modifier.fillMaxWidth().testTag("login_button")
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
