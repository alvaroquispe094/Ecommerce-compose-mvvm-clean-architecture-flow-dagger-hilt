package com.groupal.ecommerce.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.groupal.ecommerce.presentation.navigation.BottomNavigationScreens
import com.groupal.ecommerce.presentation.navigation.MainNavigation
import com.groupal.ecommerce.presentation.navigation.bottomNavigationItems

@Composable
fun AppScreen(
    authNavController: NavController
) {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TitleBar(navController, bottomNavigationItems)
        },
        content = { contentPadding ->
            MainNavigation(navController = navController, contentPadding) },
        bottomBar = {
            BottomNav(navController, bottomNavigationItems)
        }
    )
}
@Composable
fun TitleBar(navController: NavHostController, items: List<BottomNavigationScreens>) {
    TopAppBar(
        title = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    if (currentRoute == screen.route) {
                        Text(text = screen.label)
                    }
                }
            }
        }
    )
}

@Composable
fun BottomNav(navController: NavHostController, items: List<BottomNavigationScreens>) {
    BottomAppBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                selected = currentRoute == screen.route,
                icon = { Icon(painter = painterResource(screen.icon), contentDescription = "icon for navigation item") },
                label = { Text(text = screen.label) },
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

