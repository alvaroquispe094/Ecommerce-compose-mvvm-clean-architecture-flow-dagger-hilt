package com.groupal.shared.ecommerce.data.network.interceptor

import okhttp3.logging.HttpLoggingInterceptor

object DatafastLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }
}
