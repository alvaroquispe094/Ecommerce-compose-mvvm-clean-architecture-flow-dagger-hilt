package com.groupal.user.ecommerce.data.network.model

import com.groupal.shared.ecommerce.data.network.model.ImageRest
import com.groupal.user.ecommerce.domain.SignUpRequest
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpRequestRest(
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
) {
    companion object {
        fun fromDomain(signUpRequest: SignUpRequest) = SignUpRequestRest(
            id = null,
            firstName = signUpRequest.firstName,
            lastName = signUpRequest.lastName,
            email = signUpRequest.email,
            password = signUpRequest.password,
            birthdate = signUpRequest.birthdate,
            gender = signUpRequest.gender,
            phone = signUpRequest.phone,
            avatar = ImageRest(url = signUpRequest.avatar.url)
        )
    }
}