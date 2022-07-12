package com.groupal.ecommerce.domain.repository

import com.groupal.ecommerce.data.remote.dto.ProductDto

interface IProduct {
    suspend fun getProducts(): List<ProductDto>
    suspend fun getProductById(productId: String): ProductDto
}