package com.groupal.configuration.ecommerce.domain

enum class ConfigurationError(val description: String) {
    FEATURE_FLAGS_ERROR("No se pudieron obtener las configuraciones iniciales de la aplicacion")
}