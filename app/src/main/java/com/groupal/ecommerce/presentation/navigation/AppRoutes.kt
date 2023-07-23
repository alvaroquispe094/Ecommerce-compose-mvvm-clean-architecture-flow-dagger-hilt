package com.groupal.ecommerce.presentation.navigation

import com.groupal.ecommerce.R

enum class MainRoute {
    Home,
    Charge,
    Account,
    Profile,
    Registration,
    LegalRepresentative,
    MposPurchase
}

sealed class AppRoutes(val route: String){
    object LoginScreen: AppRoutes("login")
    object SignUpScreen: AppRoutes("signup")
    object AccountCreatedScreen: AppRoutes("acountcreated")
    object HomeScreen: AppRoutes("home")
}

sealed class BottomNavigationRoutes(val route: String, val label: String, val icon: Int) {
    object Home : BottomNavigationRoutes("home", "Home", R.drawable.ic_home)
    object Shop : BottomNavigationRoutes("shop", "Search", R.drawable.ic_search)
    object Favorites : BottomNavigationRoutes("favorites", "Favorite", R.drawable.ic_favorite)
    object Cart : BottomNavigationRoutes("cart", "Cart", R.drawable.ic_cart)
    object Account : BottomNavigationRoutes("account", "Account", R.drawable.ic_user)
}

val bottomNavigationItems = listOf(
    BottomNavigationRoutes.Home,
    BottomNavigationRoutes.Shop,
    BottomNavigationRoutes.Favorites,
    BottomNavigationRoutes.Cart,
    BottomNavigationRoutes.Account
)