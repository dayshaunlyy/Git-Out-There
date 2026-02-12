package com.example.gitoutthere.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitoutthere.database.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepo: UserRepository) : ViewModel() {

    fun login(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val ok = userRepo.authenticate(username.trim(), password)
            onResult(ok)
        }
    }
}
