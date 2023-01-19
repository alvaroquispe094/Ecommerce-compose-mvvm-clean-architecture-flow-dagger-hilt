package com.groupal.product.ecommerce.data.network.model

import com.groupal.product.ecommerce.domain.Product
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductRest(
    val id: Int?,
    val code: String,
    val name: String,
    val description: String,
    val price: Double,
    val image: String,
    val stock: Int,
    val categoryId: Int,
) {
    fun toDomain(): Product = Product(
        id = id,
        code = code,
        name = name,
        description = description,
        price = price,
        image = image,
        stock = stock,
        categoryId = categoryId,
    )
}