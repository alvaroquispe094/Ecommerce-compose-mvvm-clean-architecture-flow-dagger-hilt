package com.groupal.shared.ecommerce.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponseRest(
     val statusCode: Int?,
     val message: String?,
     @Json(name = "error")
     val type: String?,
)