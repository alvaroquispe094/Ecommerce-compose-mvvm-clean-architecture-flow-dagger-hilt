package com.groupal.user.ecommerce.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestRest( //TODO: Analizar el sufijo REST
    val username: String,
    val password: String
)