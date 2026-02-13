package com.example.gitoutthere.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitoutthere.database.entities.User
import com.example.gitoutthere.database.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepo: UserRepository) : ViewModel() {

    fun login(username: String, password: String, onResult: (User?) -> Unit) {
        viewModelScope.launch {
            val user = userRepo.getUser(username.trim(), password)
            onResult(user)
        }
    }
}
