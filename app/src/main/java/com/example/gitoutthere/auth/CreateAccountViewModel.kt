package com.example.gitoutthere.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitoutthere.database.entities.User
import com.example.gitoutthere.database.repository.UserRepository
import kotlinx.coroutines.launch

class CreateAccountViewModel(private val repo: UserRepository) : ViewModel() {

    fun createAccount(username: String, password: String, onResult: (String?) -> Unit) {
        viewModelScope.launch {
            val user = User(username = username.trim(), password = password)
            val result = repo.createUser(user)
            if (result.isSuccess) {
                onResult(null)
            } else {
                onResult(result.exceptionOrNull()?.message)
            }
        }
    }
}
