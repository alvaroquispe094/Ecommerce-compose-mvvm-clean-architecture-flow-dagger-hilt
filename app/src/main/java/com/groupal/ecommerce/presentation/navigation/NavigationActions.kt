package com.groupal.ecommerce.presentation.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Destinations used in the [JetnewsApp].
 */
object EcommerceDestinations {
    const val HOME_ROUTE = "home"
    const val INTERESTS_ROUTE = "interests"

    const val LOGIN_ROUTE = "login"
    const val SIGNUP_ROUTE = "signup"
}

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(NavigationScreens.Main.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    val navigateToLogin: () -> Unit = {
        navController.navigate(EcommerceDestinations.LOGIN_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
