package com.groupal.ecommerce.presentation.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.groupal.ecommerce.common.enums.HomeScreenEnum
import com.groupal.ecommerce.presentation.auth.login.LoginUiState

@Composable
fun SignupRoute(
    signupViewModel: SignupViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
) {
    // UiState of the HomeScreen, escucha todo los que se actualize en state y redibuja todo a partir de aquí
    val state by signupViewModel.state.collectAsState()

//    when (getLoginScreenType(isExpandedScreen, state)) {
//        //pantalla principal de home
//        HomeScreenEnum.Login -> {
            SignupScreen(state,signupViewModel, navigateToHome)
//        }
//        //pantalla de registro
//        HomeScreenEnum.Register -> {
////            RegisterScreen(openDrawer,state,loginViewModel, navigateToHome)
//        }
//        //pantalla de detalle de producto seleccionado
//        HomeScreenEnum.Home -> {
//            navigateToHome()
//        }
//
//        else -> {
//            LoginScreen(openDrawer, state, loginViewModel, navigateToHome)
//        }
//    }
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