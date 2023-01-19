package com.groupal.configuration.ecommerce.data.network

import com.groupal.configuration.ecommerce.data.network.model.FeatureFlagsRest
import retrofit2.Response
import retrofit2.http.GET

interface ConfigurationEndpoint {
    @GET("/api/v1/configuration")
    suspend fun getConfiguration(): Response<FeatureFlagsRest>
}