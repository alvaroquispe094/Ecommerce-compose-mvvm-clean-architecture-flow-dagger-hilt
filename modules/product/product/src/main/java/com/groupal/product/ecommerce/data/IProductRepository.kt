package com.groupal.product.ecommerce.data

import com.groupal.product.ecommerce.domain.Product

interface IProductRepository {
    suspend fun getProducts(): List<Product>
}