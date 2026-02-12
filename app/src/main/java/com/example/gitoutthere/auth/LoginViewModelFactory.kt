package com.example.gitoutthere.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitoutthere.database.repository.SessionRepository
import com.example.gitoutthere.database.repository.UserRepository

class LoginViewModelFactory (private val userRepo: UserRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create (modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}