package com.groupal.ecommerce.domain.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") var id: String,
    @SerializedName("title") var title: String,
    @SerializedName("price") var price: Double,
    @SerializedName("description") var description: String,
    @SerializedName("category") var category: String,
    @SerializedName("image") var image: String,
    @SerializedName("rating") var rating: Rating,
)
