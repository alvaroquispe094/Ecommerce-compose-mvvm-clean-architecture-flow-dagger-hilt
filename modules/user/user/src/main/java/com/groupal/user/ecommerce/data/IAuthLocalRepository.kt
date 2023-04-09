package com.groupal.user.ecommerce.data

import com.groupal.user.ecommerce.domain.Login
import kotlinx.coroutines.flow.Flow

interface IAuthLocalRepository {
    suspend fun saveSession(login: Login)

    suspend fun clear()

    val data: Flow<String?>
}
