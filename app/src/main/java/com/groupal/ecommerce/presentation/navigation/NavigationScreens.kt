package com.groupal.ecommerce.presentation.navigation

import com.groupal.ecommerce.R

sealed class NavigationScreens(val route: String) {
    object Login : NavigationScreens("login")
    object Signup : NavigationScreens("signup")
    object Main : NavigationScreens("main")
}

sealed class BottomNavigationScreens(val route: String, val label: String, val icon: Int) {
    object Home : BottomNavigationScreens("home", "Home", R.drawable.ic_menu_logo)
    object Shop : BottomNavigationScreens("shop", "Shop", R.drawable.ic_menu_logo)
    object Favorites : BottomNavigationScreens("favorites", "Favorites", R.drawable.ic_menu_logo)
    object Account : BottomNavigationScreens("account", "Account", R.drawable.ic_menu_logo)
}

val bottomNavigationItems = listOf(
    BottomNavigationScreens.Home,
    BottomNavigationScreens.Shop,
    BottomNavigationScreens.Favorites,
    BottomNavigationScreens.Account
)