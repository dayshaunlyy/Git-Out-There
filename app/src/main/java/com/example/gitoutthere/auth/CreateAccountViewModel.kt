package com.example.gitoutthere.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitoutthere.database.repository.UserRepository
import kotlinx.coroutines.launch

class CreateAccountViewModel(private val repo: UserRepository) : ViewModel() {

    fun createAccount(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val ok = repo.authenticate(username.trim(), password)
            onResult(ok)
        }
    }
}
