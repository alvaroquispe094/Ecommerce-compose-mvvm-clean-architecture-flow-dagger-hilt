package com.groupal.ecommerce.domain.dao

import com.groupal.ecommerce.data.remote.dto.ProductDto

interface ProductDao {
    suspend fun getProducts(): List<ProductDto>
    suspend fun getProductById(coinId: String): ProductDto
}