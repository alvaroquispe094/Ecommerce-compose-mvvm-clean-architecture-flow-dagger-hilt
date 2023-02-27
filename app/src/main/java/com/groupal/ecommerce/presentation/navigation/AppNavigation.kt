package com.groupal.ecommerce.presentation.navigation

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.groupal.ecommerce.presentation.screens.main.MainScreen
import com.groupal.ecommerce.presentation.viewmodel.MainViewModel
import com.groupal.shared.ecommerce.presentation.components.SnackBar
import com.groupal.shared.ecommerce.service.SnackBarState
import com.groupal.shared.ecommerce.utils.MessageType
import com.groupal.user.ecommerce.presentation.navigation.AuthNavigation
import com.groupal.shared.presentation.R as RShared

@Composable
fun AppNavigation(
    mainViewModel: MainViewModel = hiltViewModel(),
){
    val snackbarHostState = remember{ SnackbarHostState() }

    val signUpError  by mainViewModel.signUpError.collectAsState()
    val snackBar  by mainViewModel.snackBar.collectAsState()

    val message = getMessage(snackBar)

    // Muestra error generico al fallar el backend
    LaunchedEffect(signUpError) {
        if (signUpError != null) {
            mainViewModel.cleanSignUpError()
        }
    }

    LaunchedEffect(snackBar) {
        if (snackBar != null) {
            mainViewModel.show(
                snackbarHostState,
                getTypeMessage(snackBar),
                message,
            )
            mainViewModel.cleanSnackBar()
        }

    }

    AuthNavigation { MainScreen(onLogout = it) }

    SnackBar(
        snackbarHostState = snackbarHostState,
        mainViewModel.snackbarBackgroundColor,
    )
}

//@Composable
private fun getTypeMessage(
    snackBar: String?
): SnackBarState{

    return when (snackBar) {
        MessageType.Success.code ->
            MessageType.Success.typeError
        MessageType.SuccessLogin.code ->
            MessageType.SuccessLogin.typeError
        MessageType.ErrorGeneric.code ->
            MessageType.ErrorGeneric.typeError
        MessageType.ErrorUserCreation.code ->
            MessageType.ErrorUserCreation.typeError
        else -> {
            SnackBarState.ERROR
        }
    }
}

@Composable
private fun getMessage(
    snackBar: String?
): String{

    return when (snackBar) {
        MessageType.Success.code ->
            stringResource(RShared.string.snack_bar_success)
        MessageType.SuccessLogin.code ->
            stringResource(RShared.string.login_success)
        MessageType.ErrorLogin.code ->
            stringResource(RShared.string.login_error)
        MessageType.ErrorGeneric.code ->
            stringResource(RShared.string.snack_bar_error)
        MessageType.ErrorUserCreation.code ->
            stringResource(RShared.string.snack_bar_user_creation_error)
        else -> {
            stringResource(RShared.string.snack_bar_error)
        }
    }
}