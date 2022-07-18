package com.groupal.ecommerce.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.groupal.ecommerce.presentation.home.HomeRoute

@Composable
fun MainNavigation(
    authController: NavController,
    navController: NavHostController,
    contentPadding: PaddingValues

){
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.Home.route
    ) {
//        val navigationActions = NavigationActions(authController)

        composable(BottomNavigationScreens.Home.route) {
            HomeRoute(
                navigateToLogin = { authController.navigate(NavigationScreens.Login.route) }
            )
        }
        composable(BottomNavigationScreens.Shop.route) {
            // Screen content
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                Text(
                    text = "Shop screen",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        composable(BottomNavigationScreens.Favorites.route) {
            // Screen content
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                Text(
                    text = "Favorites screen",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        composable(BottomNavigationScreens.Account.route) {
            // Screen content
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                Text(
                    text = "Account screen",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}