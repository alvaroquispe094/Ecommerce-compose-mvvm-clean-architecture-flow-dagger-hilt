package com.groupal.configuration.ecommerce.data

import com.groupal.configuration.ecommerce.domain.FeatureFlags

interface IConfigurationRepository {
    suspend fun getConfiguration(): FeatureFlags
}