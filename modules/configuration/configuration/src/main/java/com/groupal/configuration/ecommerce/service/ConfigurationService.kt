package com.groupal.configuration.ecommerce.service

import com.groupal.configuration.ecommerce.data.IConfigurationLocalRepository
import com.groupal.configuration.ecommerce.data.IConfigurationRepository
import com.groupal.configuration.ecommerce.domain.FeatureFlags
import com.groupal.shared.ecommerce.di.ApplicationCoroutineScope
import com.groupal.shared.ecommerce.di.IODispatcher
import com.groupal.shared.ecommerce.domain.GenericException
import com.groupal.shared.ecommerce.service.LogService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigurationService @Inject constructor(
    @ApplicationCoroutineScope private val configurationScope: CoroutineScope,
    private val logService: LogService,
    private val configurationRepository: IConfigurationRepository,
    private val configurationLocalRepository: IConfigurationLocalRepository,
    @IODispatcher ioDispatcher: CoroutineDispatcher
) {

    init {
        configurationScope.launch(ioDispatcher) {
            getConfigurations()
        }
    }

    private val networkConfiguration = MutableStateFlow<FeatureFlags?>(null)

    val configuration: StateFlow<FeatureFlags> = configurationLocalRepository.configuration.combine(networkConfiguration) {
            local, network -> network ?: local
    }.stateIn(configurationScope, SharingStarted.Eagerly,
        FeatureFlags(isSignUpEnable = false)
    )

    private val _initConfigurationError = MutableStateFlow<GenericException?>(null)
    val configurationError: StateFlow<GenericException?> get() = _initConfigurationError.asStateFlow()
    //TODO: Implementar manejo de errores

    private suspend fun getConfigurations() {
        logService.info("CONFIGURATION", "GET FEATURE FLAGS")
        try {
            val configuration = configurationRepository.getConfiguration()
            logService.info("CONFIGURATION", configuration.toString())
            networkConfiguration.emit(configuration)
            configurationLocalRepository.update(configuration)
        } catch (e: GenericException) {
            logService.error("CONFIGURATION", e.message.toString(), e.cause)
            _initConfigurationError.emit(e)
        }
    }
}