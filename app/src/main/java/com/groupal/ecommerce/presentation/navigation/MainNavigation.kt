package com.groupal.ecommerce.presentation.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.groupal.ecommerce.presentation.screens.home.HomeScreen
import com.groupal.ecommerce.presentation.theme.Blue500
import com.groupal.shared.ecommerce.presentation.components.SearchScreen
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme

@Composable
fun MainNavigation(
    onLogout: () -> Unit,
    navController: NavHostController

){
    var search by rememberSaveable { mutableStateOf("") }

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
                SearchScreen(
                    value = search,
                    onValueChange = {
                        search = it

                     },
                )
                Text(
                    text = "Search screen",
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
        composable(BottomNavigationRoutes.Cart.route) {
            // Screen content
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                Text(
                    text = "Cart screen",
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
                //logout button
                TextButton(
                    modifier = Modifier
                        .padding(start = LocalTheme.current.padding.medium)
                        .align(Alignment.Center),
                    onClick = { onLogout() },
                    border = BorderStroke(width = 1.dp, color = Blue500)
                ) {
                    Text(text = "Logout")
                }
            }
        }
    }
}