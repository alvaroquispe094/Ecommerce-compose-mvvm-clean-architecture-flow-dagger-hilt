package com.groupal.ecommerce.presentation.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.groupal.ecommerce.common.enums.HomeScreenEnum

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
) {
    // UiState of the HomeScreen, escucha todo los que se actualize en state y redibuja todo a partir de aquí
    val state by loginViewModel.state.collectAsState()

    when (getLoginScreenType(state)) {
        //pantalla principal de home
        HomeScreenEnum.Login -> {
            LoginScreen(state,loginViewModel, navigateToHome)
        }
        //pantalla de registro
        HomeScreenEnum.Register -> {
//            RegisterScreen(openDrawer,state,loginViewModel, navigateToHome)
        }
//        //pantalla de detalle de producto seleccionado
        HomeScreenEnum.Home -> {
            navigateToHome()
        }

        else -> {
            LoginScreen(state,loginViewModel, navigateToHome)
        }
    }
}

/**
 * Returns the current [HomeScreenType] to display, based on whether or not the screen is expanded
 * and the [HomeUiState].
 */
@Composable
private fun getLoginScreenType(
    state: LoginUiState
): HomeScreenEnum {
    return if(state.isLoginOpen) {
        HomeScreenEnum.Login
    } else {
        HomeScreenEnum.Home
    }
}