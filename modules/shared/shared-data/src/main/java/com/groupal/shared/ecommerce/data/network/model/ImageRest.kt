package com.groupal.shared.ecommerce.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageRest(
    val url: String
)
