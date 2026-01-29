package com.example.gitoutthere.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitoutthere.database.repository.UserRepository
import kotlinx.coroutines.launch


class LoginViewModel( private val repo: UserRepository): ViewModel() {

    var loggedIn: Boolean = false
        private set

    var loginFailed: Boolean = false
        private set

    fun login (username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = repo.authenticate(username.trim(), password)
            loggedIn = result
            loginFailed = result
            onResult(result)
        }
    }
}