package com.groupal.ecommerce.presentation.auth.login

data class LoginUiState (
    val isLoginOpen: Boolean = true,
    val isLoading: Boolean = false,
    val error: String = "",
    val user: String = "",
    val password: String = ""
)