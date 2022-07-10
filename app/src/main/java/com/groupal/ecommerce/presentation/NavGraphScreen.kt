package com.groupal.ecommerce.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.groupal.ecommerce.presentation.home.HomeRoute
import com.groupal.ecommerce.presentation.login.LoginRoute

@Composable
fun NavGraphScreen(
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    startDestination: String = EcommerceDestinations.LOGIN_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        val navigationActions = NavigationActions(navController)


        composable(EcommerceDestinations.HOME_ROUTE) {
            // Screen content
            HomeRoute(
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )

        }
        composable(EcommerceDestinations.INTERESTS_ROUTE) {
            // Screen content
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                Text(
                    text = "InterestScreen",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
//            HomeRoute(
//                isExpandedScreen = isExpandedScreen,
//                openDrawer = openDrawer
//            )
        }
        composable(EcommerceDestinations.LOGIN_ROUTE) {
            // Screen content
            LoginRoute(
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer,
                navigateToHome = navigationActions.navigateToHome,
            )

        }
    }
}
