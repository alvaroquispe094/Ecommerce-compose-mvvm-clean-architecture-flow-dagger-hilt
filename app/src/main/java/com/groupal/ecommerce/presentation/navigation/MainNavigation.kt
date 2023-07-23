package com.groupal.ecommerce.presentation.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.groupal.ecommerce.presentation.screens.home.HomeScreen
import com.groupal.ecommerce.presentation.theme.Blue500
import com.groupal.shared.ecommerce.presentation.components.SearchScreen
import com.groupal.shared.ecommerce.presentation.components.TopAppBar
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme
import com.groupal.user.ecommerce.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun MainNavigation(
    loginViewModel: AuthViewModel = hiltViewModel(),
    onLogout: () -> Unit,
    navController: NavHostController

){
    var search by rememberSaveable { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope() //Scope para coroutines en composables

    NavHost(
        navController = navController,
        startDestination = BottomNavigationRoutes.Home.route
    ) {
        composable(BottomNavigationRoutes.Home.route) {
            HomeScreen(navigateToLogin = onLogout)
        }
        composable(BottomNavigationRoutes.Shop.route) {
            // Screen content
            Column(modifier = Modifier
                .fillMaxSize()
            ) {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth().padding(4.dp, 0.dp),
                    shouldShowBack = false
                )
                SearchScreen(
                    value = search,
                    onValueChange = {
                        search = it

                     },
                )
                /*Text(
                    text = "Search screen",
                    modifier = Modifier.align(Alignment.Center)
                )*/
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
                    onClick = {
                        coroutineScope.launch {
                            loginViewModel.logout()
                        }
                    } ,
                    border = BorderStroke(width = 1.dp, color = Blue500)
                ) {
                    Text(text = "Logout")
                }
            }
        }
    }
}