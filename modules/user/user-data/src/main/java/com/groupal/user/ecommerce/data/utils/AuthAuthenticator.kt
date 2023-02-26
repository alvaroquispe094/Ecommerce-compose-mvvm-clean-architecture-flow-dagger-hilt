package com.groupal.user.ecommerce.data.utils

import com.groupal.configuration.ecommerce.data.local.repository.TokenManager
import com.groupal.user.ecommerce.data.network.AuthEndpoint
import com.groupal.user.ecommerce.data.network.model.LoginResponseRest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val url: String,
): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        val token = runBlocking {
            tokenManager.getToken().first()
        }
        return runBlocking {
            val newToken = getNewToken(token)

            if (!newToken.isSuccessful || newToken.body() == null) { //Couldn't refresh the token, so restart the login process
                tokenManager.deleteToken()
                tokenManager.emitToken(null)
            }

            newToken.body()?.let {
                tokenManager.saveToken(it.accessToken)
                tokenManager.emitToken(it.accessToken)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.accessToken}")
                    .build()
            }
        }
    }

    private suspend fun getNewToken(refreshToken: String?): retrofit2.Response<LoginResponseRest> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
        val service = retrofit.create(AuthEndpoint::class.java)
        return service.refreshToken("Bearer $refreshToken")
    }
}