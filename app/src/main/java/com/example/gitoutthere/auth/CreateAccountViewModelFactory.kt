package com.example.gitoutthere.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitoutthere.database.repository.UserRepository

class CreateAccountViewModelFactory (private val repo: UserRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create (modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CreateAccountViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CreateAccountViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}