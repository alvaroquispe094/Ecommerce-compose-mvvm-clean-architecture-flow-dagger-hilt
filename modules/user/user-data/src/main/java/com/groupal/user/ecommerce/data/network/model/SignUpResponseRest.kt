package com.groupal.user.ecommerce.data.network.model

import com.groupal.shared.ecommerce.data.network.model.ImageRest
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpResponseRest(
    val id: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val gender: String,
    val birthdate: String,
    @Json(name = "image")
    val avatar: ImageRest,
    val phone: String
)
