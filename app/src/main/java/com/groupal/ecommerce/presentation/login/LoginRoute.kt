package com.groupal.ecommerce.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.groupal.ecommerce.common.enums.HomeScreenEnum

@Composable
fun LoginRoute(
    isExpandedScreen: Boolean,
    loginViewModel: LoginViewModel = hiltViewModel(),
    openDrawer: () -> Unit,
    navigateToHome: () -> Unit,
) {
    // UiState of the HomeScreen, escucha todo los que se actualize en state y redibuja todo a partir de aquí
    val state by loginViewModel.state.collectAsState()

    when (getLoginScreenType(isExpandedScreen, state)) {
        //pantalla principal de home
        HomeScreenEnum.Login -> {
            LoginScreen(openDrawer,state,loginViewModel, navigateToHome)
        }
        //pantalla de detalle de producto seleccionado
        HomeScreenEnum.Home -> {
            navigateToHome()
        }

        else -> {
            LoginScreen(openDrawer, state, loginViewModel, navigateToHome)
        }
    }
}

/**
 * Returns the current [HomeScreenType] to display, based on whether or not the screen is expanded
 * and the [HomeUiState].
 */
@Composable
private fun getLoginScreenType(
    isExpandedScreen: Boolean,
    state: LoginUiState
): HomeScreenEnum = when (isExpandedScreen) {
    //si no esta en landscape
    false -> {
        if (state.isLoginOpen) {
            HomeScreenEnum.Login
        } else {
            HomeScreenEnum.Home
        }
    }// si esta en landscape, cambiar logica para visualizar de otra manera
    true -> {
        if (state.isLoginOpen) {
            HomeScreenEnum.Login
        } else {
            HomeScreenEnum.Home
        }
    }

}