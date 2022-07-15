package com.groupal.ecommerce.presentation.auth

import com.groupal.ecommerce.presentation.auth.login.LoginUiState
import com.groupal.ecommerce.presentation.auth.register.SignupUiState

data class AuthUiState (
    val loginUiState: LoginUiState,
    val registerUiState: SignupUiState,
)