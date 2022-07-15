package com.groupal.ecommerce.presentation.login

data class LoginUiState (
    val isLoginOpen: Boolean = true,
    val isLoading: Boolean = false,
    val error: String = "",
    val user: String = "",
    val password: String = ""
)