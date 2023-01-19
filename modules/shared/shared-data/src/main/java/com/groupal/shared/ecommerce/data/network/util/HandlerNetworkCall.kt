package com.groupal.shared.ecommerce.data.network.util

import android.util.Log
import com.groupal.shared.ecommerce.data.network.model.ErrorResponseRest
import com.groupal.shared.ecommerce.data.network.model.Result
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> execute(apiCall: suspend () -> Response<T>): Result<T?> {
    try {
        val response = apiCall.invoke()
        if (response.isSuccessful) {
            return Result.Success(response.body())
        }
        throw HttpException(response)
    } catch (throwable: Throwable) {
        return when (throwable) {
            is IOException -> {
                Log.e("Error-IO", throwable.message.toString()) //TODO: Deberia utilizar servicio-log centralizado
                Result.NetworkError
            }
            is HttpException -> {
                val errorResponse = mapError(throwable)
                Log.e("Error-HTTP", errorResponse.toString())
                Result.GenericError(errorResponse)
            }
            else -> {
                Log.e("Error-UNKNOWN", throwable.message.toString())
                Result.UnknownError
            }
        }
    }
}

private fun mapError(throwable: HttpException): ErrorResponseRest? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder()
                .build()
                .adapter(ErrorResponseRest::class.java)
                .lenient()
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        Log.e("Error-Map", exception.message.toString())
        null //TODO: No devuelvo nada
    }
}