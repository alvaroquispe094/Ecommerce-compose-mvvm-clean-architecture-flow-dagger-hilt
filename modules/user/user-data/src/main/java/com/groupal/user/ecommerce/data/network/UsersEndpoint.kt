package com.groupal.user.ecommerce.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersEndpoint {
    @GET("api/v1/users/exist/{email}")
    suspend fun existUserByEmail(@Path("email") email: String): Response<Boolean>
}