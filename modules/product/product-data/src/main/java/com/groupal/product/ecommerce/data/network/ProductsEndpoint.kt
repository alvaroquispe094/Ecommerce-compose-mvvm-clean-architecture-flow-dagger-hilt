package com.groupal.product.ecommerce.data.network

import com.groupal.product.ecommerce.data.network.model.ProductRest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ProductsEndpoint {
    //@Headers("Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqZ29uemFsZXpAZ21haWwuY29tIiwiaWF0IjoxNjczOTAzODc3LCJleHAiOjE2NzM5OTAyNzd9.5Seg0qtvCTLewPtf7aKR5FrERhKH3HCHFXfy3tMZHb06anxGUAmvN8KsD00xl4L-OpBnYzBZt2MwI7wH0Bwyxg")
    @GET("api/v1/product")
    suspend fun getProducts(): Response<List<ProductRest>>
}