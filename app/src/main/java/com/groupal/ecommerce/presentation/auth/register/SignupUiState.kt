package com.groupal.ecommerce.presentation.auth.register

data class SignupUiState (
    val isRegisterOpen: Boolean = true,
    val isLoading: Boolean = false,
    val error: String = "",
    val name: String = "",
    val surname: String = "",
    val user: String = "",
    val password: String = "",
    val address: String = ""
)