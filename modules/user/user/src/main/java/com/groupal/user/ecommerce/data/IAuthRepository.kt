package com.groupal.user.ecommerce.data

import com.groupal.user.ecommerce.domain.LoginResponse
import com.groupal.user.ecommerce.domain.SignUpRequest


interface IAuthRepository {
    suspend fun login(email: String, password: String): LoginResponse
    suspend fun signUp(signUpRequest: SignUpRequest)
}