package com.groupal.shared.ecommerce.utils

import com.groupal.shared.ecommerce.service.SnackBarState

sealed class MessageType(val code: String, val typeError: SnackBarState){
    object Success: MessageType("success_generic", SnackBarState.SUCCESS)
    object SuccessLogin: MessageType("success_login", SnackBarState.DEFAULT)
    object ErrorLogin: MessageType("error_login", SnackBarState.ERROR)
    object ErrorGeneric: MessageType("error_generic", SnackBarState.ERROR)
    object ErrorUserCreation: MessageType("error_user_creation", SnackBarState.ERROR)
}