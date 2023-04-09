package com.groupal.configuration.ecommerce.data

import com.groupal.configuration.ecommerce.domain.ConfigurationStorage
import kotlinx.coroutines.flow.Flow

interface IConfigurationLocalRepository {

    val configuration: Flow<ConfigurationStorage>

    suspend fun updateOnBoardingStage(onBoardingStage: Int)

    suspend fun clear()
}
