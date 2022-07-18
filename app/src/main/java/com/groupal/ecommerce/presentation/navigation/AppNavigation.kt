package com.groupal.ecommerce.presentation.navigation

import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.groupal.ecommerce.presentation.AppScreen
import com.groupal.ecommerce.presentation.auth.login.LoginRoute
import com.groupal.ecommerce.presentation.auth.register.SignupRoute
import com.groupal.ecommerce.presentation.home.HomeRoute

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationScreens.Login.route
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier
    ) {
        val navigationActions = NavigationActions(navController)

        composable(NavigationScreens.Login.route) {
            // Screen content
            LoginRoute(
                navigateToHome = navigationActions.navigateToHome,
                navigateToSignup = navigationActions.navigateToSignup,
            )
        }
        composable(NavigationScreens.Signup.route) {
            // Screen content
            SignupRoute(
                navController
            )
        }
        composable(NavigationScreens.Main.route) {
            // Screen content
            AppScreen(authNavController = navController)
        }
    }
}