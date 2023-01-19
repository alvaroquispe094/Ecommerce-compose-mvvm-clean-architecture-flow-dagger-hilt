package com.groupal.user.ecommerce.data.network.model

import com.groupal.user.ecommerce.domain.LoginResponse
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponseRest(
    val accessToken: String,
    val id: Long,
    val username: String,
    val email: String,
    val roles: List<String>,
    var tokenType: String = "Bearer"
) {
    fun toDomain() = LoginResponse(
        accessToken = accessToken,
        id = id,
        username = username,
        email = email,
        roles = roles,
        tokenType = tokenType,
    )
}