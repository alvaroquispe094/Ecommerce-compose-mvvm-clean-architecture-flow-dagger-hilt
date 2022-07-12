package com.groupal.ecommerce.domain.model

data class User (
    val id:Long,
    val mobNum: String,
    val password: String,
    val name:String,
    val profession:String,
    val email:String
)