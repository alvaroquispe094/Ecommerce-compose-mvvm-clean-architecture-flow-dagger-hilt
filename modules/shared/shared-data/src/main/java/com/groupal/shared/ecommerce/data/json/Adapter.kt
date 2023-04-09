package com.groupal.shared.ecommerce.data.json

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun <T> getJsonAdapter(klass: Class<T>): JsonAdapter<T> =
    Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
        .adapter(klass)
        .lenient()
