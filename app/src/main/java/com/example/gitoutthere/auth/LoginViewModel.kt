package com.example.gitoutthere.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitoutthere.database.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data object Success : LoginState()
    data object Failed : LoginState()
}

class LoginViewModel(
    private val repo: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state

    fun login(username: String, password: String) {
        // Basic guard (optional)
        if (username.isBlank() || password.isBlank()) {
            _state.value = LoginState.Failed
            return
        }

        _state.value = LoginState.Loading

        viewModelScope.launch {
            val ok = repo.authenticate(username.trim(), password)
            _state.value = if (ok) LoginState.Success else LoginState.Failed
        }
    }

    fun resetToIdle() {
        _state.value = LoginState.Idle
    }
}
