package com.groupal.user.ecommerce.presentation.navigation

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.groupal.shared.ecommerce.presentation.LocalNavController
import com.groupal.shared.ecommerce.presentation.util.collectAsStateLifecycleAware
import com.groupal.user.ecommerce.domain.LoginResponse
import com.groupal.user.ecommerce.presentation.screen.AccountCreatedScreen
import com.groupal.user.ecommerce.presentation.screen.LoginScreen
import com.groupal.user.ecommerce.presentation.screen.SignUpScreen
import com.groupal.user.ecommerce.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

private enum class AuthRoute(val route: String) {
    LogIn("login"),
    SignUp("signup"),
    Created("created"),
    Authenticated("authenticated")
}

private fun destinationByLoginSession(loginSession: LoginResponse?): AuthRoute =
    if (loginSession == null) AuthRoute.LogIn else AuthRoute.Authenticated

private fun destinationByTokenSession(token: String?): AuthRoute =
    if (token == null) AuthRoute.LogIn else AuthRoute.Authenticated


@Composable
fun AuthNavigation(
    loginViewModel: AuthViewModel = hiltViewModel(),
    //authenticated: @Composable (onLogout: () -> Unit) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val loginSession by loginViewModel.loginSession.collectAsState()
    val isSignUpOk by loginViewModel.isSignUpOk.collectAsState()
    //val sessionToken by loginViewModel.sessionToken.collectAsStateLifecycleAware()
    //val isLoggedIn by loginViewModel.isLoggedIn.collectAsStateLifecycleAware()

    val startingRoute =  AuthRoute.LogIn

    /*LaunchedEffect(isSignUpOk) {
        if(isSignUpOk) {
            navController.navigate(AuthRoute.Created.route)
        }
    }*/

    CompositionLocalProvider(LocalNavController provides navController) {

        NavHost(
            navController = navController,
            startDestination = startingRoute.route
        ) {
            composable(AuthRoute.LogIn.route) {
                LoginScreen {
                    navController.navigate(AuthRoute.SignUp.route)
                }
            }
            composable(AuthRoute.SignUp.route){
                SignUpScreen {
                    navController.navigate(AuthRoute.LogIn.route)
                }
            }
            composable(AuthRoute.Created.route) {
                AccountCreatedScreen {
                    navController.navigate(AuthRoute.LogIn.route)
                }
            }
            /*composable(AuthRoute.Authenticated.route) {
                authenticated {
                    coroutineScope.launch {
                        loginViewModel.logout()
                    }
                }
            }*/
        }

    }


}