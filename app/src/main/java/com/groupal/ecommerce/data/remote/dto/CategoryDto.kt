package com.groupal.ecommerce.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.groupal.ecommerce.domain.model.Category

class CategoryDto (
    @SerializedName("category") var category: String,
    @SerializedName("path") var path: String,
)

// Just like a mapper
fun CategoryDto.toCategory(): Category {
    return Category(
        category = category,
        path = path
    )
}