package com.example.gitoutthere.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitoutthere.database.entities.User
import com.example.gitoutthere.database.repository.SessionRepository
import com.example.gitoutthere.database.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepo: UserRepository, private val sessionRepo: SessionRepository) : ViewModel() {

    fun login(username: String, password: String, onResult: (User?) -> Unit) {
        viewModelScope.launch {
            val user = repo.getUser(username.trim(), password)
            onResult(user)
            val ok = userRepo.authenticate(username.trim(), password)
            if(ok) sessionRepo.save(username)
            onResult(ok)
        }
    }
}
