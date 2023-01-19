package com.groupal.shared.ecommerce.data.network.model

sealed class Result<out T> {
    data class Success<out T>(val value: T): Result<T>()
    data class GenericError(val error: ErrorResponseRest?): Result<Nothing>()
    object NetworkError: Result<Nothing>()
    object UnknownError: Result<Nothing>()
}