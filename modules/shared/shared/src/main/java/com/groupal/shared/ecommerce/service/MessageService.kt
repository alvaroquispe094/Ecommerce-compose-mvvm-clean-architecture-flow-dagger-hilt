package com.groupal.shared.ecommerce.service

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.ui.graphics.Color
import com.groupal.shared.ecommerce.domain.GenericException
import com.groupal.shared.ecommerce.utils.MessageType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageService @Inject constructor() {

    //snackBar
    private val _snackBar = MutableStateFlow<GenericException?>(null)
    val snackBar: StateFlow<GenericException?> get() = _snackBar.asStateFlow()


    private var snackbarState: SnackBarState = SnackBarState.DEFAULT

    val snackbarBackgroundColor: Color
    get() = when (snackbarState) {
        SnackBarState.DEFAULT -> Color(0xFF3EAAFF)
        SnackBarState.SUCCESS -> Color(0xFF009688)
        SnackBarState.ERROR -> Color(0xFFE91E63)
    }

    suspend fun showSnackBar(snackbarHostState: SnackbarHostState, snackbarState: SnackBarState, message: String) {
        this.snackbarState = snackbarState
        snackbarHostState.showSnackbar(
            message = message,
            duration = SnackbarDuration.Indefinite,
        )
    }

    suspend fun showMessage(errorType: MessageType) {
        when (errorType) {
            MessageType.Success -> _snackBar.emit(GenericException(MessageType.Success.code))
            MessageType.SuccessLogin -> _snackBar.emit(GenericException(MessageType.SuccessLogin.code))
            MessageType.ErrorLogin -> _snackBar.emit(GenericException(MessageType.ErrorLogin.code))
            MessageType.ErrorGeneric -> _snackBar.emit(GenericException(MessageType.ErrorGeneric.code))
            MessageType.ErrorUserCreation -> _snackBar.emit(GenericException(MessageType.ErrorUserCreation.code))
        }
    }

    suspend fun cleanSnackBar() {
        _snackBar.emit(null)
    }

}


enum class SnackBarState {
    DEFAULT,
    SUCCESS,
    ERROR
}