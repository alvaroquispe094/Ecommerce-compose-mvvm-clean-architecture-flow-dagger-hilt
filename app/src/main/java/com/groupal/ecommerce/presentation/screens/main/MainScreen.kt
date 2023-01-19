package com.groupal.ecommerce.presentation.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.groupal.ecommerce.presentation.navigation.BottomNavigationRoutes
import com.groupal.ecommerce.presentation.navigation.MainNavigation
import com.groupal.ecommerce.presentation.navigation.bottomNavigationItems
import com.groupal.ecommerce.presentation.theme.Purple500

@Composable
fun MainScreen(
    onLogout: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                MainNavigation(onLogout = onLogout, navController = navController)
            }
        },
        bottomBar = {
            BottomNav(navController, bottomNavigationItems)
        }
    )
}
@Composable
fun TitleBar(navController: NavHostController, items: List<BottomNavigationRoutes>) {
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
fun BottomNav(navController: NavHostController, items: List<BottomNavigationRoutes>) {
    BottomAppBar(
        backgroundColor = Color.White,
        contentColor = Purple500
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                selected = currentRoute == screen.route,
                icon = { Icon(painter = painterResource(screen.icon), contentDescription = "icon for navigation item") },
                label = { Text(text = screen.label) },
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route){
                            //para guardar el estado al volver a elegir el menu bottom
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

