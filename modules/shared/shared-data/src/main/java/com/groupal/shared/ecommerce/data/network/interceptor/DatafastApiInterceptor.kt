package com.groupal.shared.ecommerce.data.network.interceptor

import okhttp3.Interceptor

class DatafastApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response =
        chain
            .request()
            .newBuilder()
            .header("x-api-key", API_KEY)
            .build()
            .let(chain::proceed)

    companion object {
        const val API_KEY = "Q4JAJ8ZEfO5dvxqRsi4ds1gCqcbY16iMPW83tc7e"
    }
}
