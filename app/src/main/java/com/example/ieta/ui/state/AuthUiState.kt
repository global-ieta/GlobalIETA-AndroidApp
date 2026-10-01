package com.example.ieta.ui.state

import com.example.ieta.domain.model.User

data class AuthUiState(
    val currentUser: User? = null,
    val isAuthenticated: Boolean = false,
    val isLoading: Boolean = false,
    val emailInput: String = "",
    val passwordInput: String = "",
    val errorMessage: String? = null
)
