package com.groupal.ecommerce.presentation.navigation

import com.groupal.ecommerce.R

sealed class AppRoutes(val route: String){
    object LoginScreen: AppRoutes("login")
    object SignUpScreen: AppRoutes("signup")
    object AccountCreatedScreen: AppRoutes("acountcreated")
    object HomeScreen: AppRoutes("home")
}

sealed class BottomNavigationRoutes(val route: String, val label: String, val icon: Int) {
    object Home : BottomNavigationRoutes("home", "Home", R.drawable.ic_home)
    object Shop : BottomNavigationRoutes("shop", "Shop", R.drawable.ic_shop)
    object Favorites : BottomNavigationRoutes("favorites", "Favorites", R.drawable.ic_favorite)
    object Account : BottomNavigationRoutes("account", "Account", R.drawable.ic_account)
}

val bottomNavigationItems = listOf(
    BottomNavigationRoutes.Home,
    BottomNavigationRoutes.Shop,
    BottomNavigationRoutes.Favorites,
    BottomNavigationRoutes.Account
)