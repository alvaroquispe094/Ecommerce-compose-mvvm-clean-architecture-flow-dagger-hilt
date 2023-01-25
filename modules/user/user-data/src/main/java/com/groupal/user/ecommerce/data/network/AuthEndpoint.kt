package com.groupal.user.ecommerce.data.network


import com.groupal.user.ecommerce.data.network.model.LoginRequestRest
import com.groupal.user.ecommerce.data.network.model.LoginResponseRest
import com.groupal.user.ecommerce.data.network.model.SignUpRequestRest
import com.groupal.user.ecommerce.data.network.model.SignUpResponseRest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthEndpoint {
    @POST("/api/v1/auth/signin")
    suspend fun login(@Body loginRequest: LoginRequestRest): Response<LoginResponseRest>

    @GET("/api/v1/auth/refresh")
    suspend fun refreshToken(@Header("Authorization") token: String): Response<LoginResponseRest>

    @POST("/api/v1/authentication/register")
    suspend fun signUp(@Body signUpRequest: SignUpRequestRest): Response<SignUpResponseRest>
}