package com.groupal.configuration.ecommerce.data.network.repository

import com.groupal.configuration.ecommerce.data.network.ConfigurationEndpoint
import com.groupal.configuration.ecommerce.data.IConfigurationRepository
import com.groupal.configuration.ecommerce.domain.ConfigurationError
import com.groupal.configuration.ecommerce.domain.FeatureFlags
import com.groupal.shared.ecommerce.data.network.util.execute
import com.groupal.shared.ecommerce.data.network.util.handlerResponse
import com.groupal.shared.ecommerce.domain.GenericException
import javax.inject.Inject

class ConfigurationRepository @Inject constructor(
    private val configurationEndpoint: ConfigurationEndpoint,
): IConfigurationRepository {

    override suspend fun getConfiguration(): FeatureFlags = handlerResponse(
        execute { configurationEndpoint.getConfiguration() }
    ) {
        it.value?.toDomain() ?: throw GenericException(ConfigurationError.FEATURE_FLAGS_ERROR.description)
    }
}