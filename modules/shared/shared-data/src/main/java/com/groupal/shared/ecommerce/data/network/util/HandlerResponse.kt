package com.groupal.shared.ecommerce.data.network.util

import com.groupal.shared.ecommerce.data.network.model.GenericError
import com.groupal.shared.ecommerce.data.network.model.Result
import com.groupal.shared.ecommerce.domain.GenericException

fun <T, V> handlerResponse(response: Result<T>, mapOnSuccess: (result: Result.Success<T>) -> V): V = when (response) {
    is Result.Success -> mapOnSuccess(response)
    is Result.GenericError -> throw GenericException(response.error?.message ?: "")
    is Result.NetworkError -> throw GenericException(GenericError.NETWORK_ERROR.description)
    is Result.UnknownError -> throw GenericException(GenericError.UNKNOWN_ERROR.description)
}
