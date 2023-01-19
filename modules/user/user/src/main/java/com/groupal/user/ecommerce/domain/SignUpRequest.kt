package com.groupal.user.ecommerce.domain

import com.groupal.shared.ecommerce.domain.Image


data class SignUpRequest (
    val id: String? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val gender: String,
    val birthdate: String,
    val avatar: Image,
    val phone: String,
)