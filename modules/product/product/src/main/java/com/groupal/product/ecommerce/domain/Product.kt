package com.groupal.product.ecommerce.domain


data class Product(
    val id: Int? = null,
    val code: String? = null,
    val name: String? = null,
    val description: String? = null,
    val price: Double? = null,
    val image: String? = null,
    val stock: Int? = null,
    val categoryId: Int? = null,
)