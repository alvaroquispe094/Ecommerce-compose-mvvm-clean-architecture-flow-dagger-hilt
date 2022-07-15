package com.groupal.ecommerce.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.groupal.ecommerce.presentation.home.HomeRoute

@Composable
fun MainNavigation(
    navController: NavHostController,
    contentPadding: PaddingValues

){
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.Home.route
    ) {
        composable(BottomNavigationScreens.Home.route) { HomeRoute() }
        composable(BottomNavigationScreens.Shop.route) { HomeRoute() }
        composable(BottomNavigationScreens.Favorites.route) { HomeRoute() }
        composable(BottomNavigationScreens.Account.route) { HomeRoute()}
    }
}