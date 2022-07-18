package com.groupal.ecommerce.presentation.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.groupal.ecommerce.common.enums.HomeScreenEnum

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToSignup: () -> Unit,

) {
    // UiState of the HomeScreen, escucha todo los que se actualize en state y redibuja todo a partir de aquí
    val state by loginViewModel.state.collectAsState()

    //Navigate to screen selected
//    val navigationActions = NavigationActions(navController)

//    val navigationActions = remember(navController) {
//        NavigationActions(navController)
//    }

    when (getLoginScreenType(state)) {
        //pantalla principal de home
        HomeScreenEnum.Login -> {
            LoginScreen(state,loginViewModel,navigateToHome,navigateToSignup)
        }
        //pantalla de registro
        HomeScreenEnum.Register -> {
            navigateToSignup()
        }
//        //pantalla Home main de la app
        HomeScreenEnum.Home -> {
            //navigationActions.navigateToHome
            navigateToHome()
//            navigationActions.navigateToHome
//            navController.navigate(NavigationScreens.Main.route)
        }

        else -> {
            LoginScreen(state,loginViewModel,navigateToHome,navigateToSignup)
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