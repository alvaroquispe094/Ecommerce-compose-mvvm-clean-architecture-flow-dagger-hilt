package com.groupal.ecommerce.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.groupal.ecommerce.presentation.screens.home.HomeScreen

@Composable
fun MainNavigation(
    onLogout: () -> Unit,
    navController: NavHostController

){
    NavHost(
        navController = navController,
        startDestination = BottomNavigationRoutes.Home.route
    ) {
        composable(BottomNavigationRoutes.Home.route) {
            HomeScreen(navigateToLogin = onLogout)
        }
        composable(BottomNavigationRoutes.Shop.route) {
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
        composable(BottomNavigationRoutes.Favorites.route) {
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
        composable(BottomNavigationRoutes.Account.route) {
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