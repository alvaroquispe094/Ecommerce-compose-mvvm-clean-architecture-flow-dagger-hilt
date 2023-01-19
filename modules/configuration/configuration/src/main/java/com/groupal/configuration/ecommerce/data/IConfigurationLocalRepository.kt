package com.groupal.configuration.ecommerce.data

import com.groupal.configuration.ecommerce.domain.FeatureFlags
import kotlinx.coroutines.flow.Flow

interface IConfigurationLocalRepository {
    val configuration: Flow<FeatureFlags>

    suspend fun update(featureFlags: FeatureFlags)
}