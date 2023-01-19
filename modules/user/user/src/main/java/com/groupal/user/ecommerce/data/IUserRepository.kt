package com.groupal.user.ecommerce.data

interface IUserRepository {
    suspend fun existUserByEmail(email: String): Boolean
}