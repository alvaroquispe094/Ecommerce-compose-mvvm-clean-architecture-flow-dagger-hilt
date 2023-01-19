package com.groupal.shared.ecommerce.data.network.model

enum class GenericError(val description: String) {
     NETWORK_ERROR("Error de conexion por favor reintente nuevamente"),
     UNKNOWN_ERROR("Error no esperado, por favor reintente nuevamente"),
     LOGIN_ERROR("Error no esperado en el login, por favor reintente nuevamente"),
     FEATURE_FLAGS_ERROR("No se pudieron obtener las configuraciones iniciales de la aplicacion")
}