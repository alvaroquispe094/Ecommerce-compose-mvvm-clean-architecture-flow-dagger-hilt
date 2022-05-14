package com.groupal.ecommerce.data.remote

import com.groupal.ecommerce.data.remote.dto.CategoryDto
import com.groupal.ecommerce.data.remote.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MockApi {
    // Endpoints product
    @GET("/product")
    suspend fun getProducts(): List<ProductDto>
    @GET("/product/{id}")
    suspend fun getProduct(@Path("id") id: String): ProductDto

    //Endpoint category
    @GET("/category")
    suspend fun getAllCategories(): List<CategoryDto>
}