package com.groupal.user.ecommerce.domain

data class LoginResponse(
    val accessToken: String,
    val id: Long,
    val username: String,
    val email: String,
    val roles: List<String>,
    var tokenType: String = "Bearer"
)