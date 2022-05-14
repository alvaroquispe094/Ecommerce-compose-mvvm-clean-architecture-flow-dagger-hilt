package com.groupal.ecommerce.data.repository

import com.groupal.ecommerce.data.remote.MockApi
import com.groupal.ecommerce.data.remote.dto.ProductDto
import com.groupal.ecommerce.domain.dao.ProductDao
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: MockApi
) : ProductDao {

    override suspend fun getProducts(): List<ProductDto> {
        return api.getProducts()
    }

    override suspend fun getProductById(productId: String): ProductDto {
        return api.getProduct(productId)
    }
}