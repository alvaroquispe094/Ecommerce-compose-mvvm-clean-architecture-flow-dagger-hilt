package com.groupal.ecommerce.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.groupal.ecommerce.domain.model.Category
import com.groupal.ecommerce.domain.model.Product
import com.groupal.ecommerce.domain.model.Rating

data class ProductDto(
    @SerializedName("id") var id: String,
    @SerializedName("title") var title: String,
    @SerializedName("price") var price: Double,
    @SerializedName("description") var description: String,
    @SerializedName("category") var category: String,
    @SerializedName("image") var image: String,
    @SerializedName("rating") var rating: Rating,
)

// Just like a mapper
fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
        rating = rating
    )
}