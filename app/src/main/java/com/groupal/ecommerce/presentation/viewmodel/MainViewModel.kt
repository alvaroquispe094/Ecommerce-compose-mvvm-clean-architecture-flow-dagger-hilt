package com.groupal.ecommerce.presentation.viewmodel

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupal.shared.ecommerce.domain.GenericException
import com.groupal.shared.ecommerce.service.MessageService
import com.groupal.shared.ecommerce.service.SnackBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val snackBarService: MessageService,
) : ViewModel() {

    // Error generic
    private val _signUpError = MutableStateFlow<GenericException?>(null)
    val signUpError: StateFlow<GenericException?> get() = _signUpError.asStateFlow()

    val snackBar: StateFlow<String?> =
        snackBarService.snackBar.map { exception -> exception?.message }
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val snackbarBackgroundColor: Color
        @Composable
        get() = snackBarService.snackbarBackgroundColor


    suspend fun show(snackbarHostState: SnackbarHostState, snackbarState: SnackBarState, message: String) {
        viewModelScope.launch(Dispatchers.IO){
            snackBarService.showSnackBar(
                snackbarHostState,
                snackbarState,
                message,
            )
        }
    }

    suspend fun cleanSnackBar() {
        snackBarService.cleanSnackBar()
    }

    suspend fun cleanSignUpError() {
        _signUpError.emit(null)
    }

}