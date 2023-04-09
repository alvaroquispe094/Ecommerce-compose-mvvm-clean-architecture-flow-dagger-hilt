package com.groupal.user.ecommerce.data.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import okhttp3.Interceptor

class AuthInterceptor2 : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response =
        chain.request().newBuilder()
            .also {
                val token = token?.value

                if (token != null) {
                    it.addHeader("Authorization", "Bearer $token")
                }
            }
            .build()
            .let(chain::proceed)

    companion object {
        private var token: StateFlow<String?>? = null

        fun configure(scope: CoroutineScope, token: Flow<String?>) {
            this.token = token.stateIn(scope, SharingStarted.Eagerly, null)
        }
    }
}
