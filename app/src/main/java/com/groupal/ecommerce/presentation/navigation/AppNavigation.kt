package com.groupal.ecommerce.presentation.navigation

import androidx.compose.runtime.Composable
import com.groupal.ecommerce.presentation.screens.main.MainScreen
import com.groupal.user.ecommerce.presentation.navigation.AuthNavigation

@Composable
fun AppNavigation(){
    AuthNavigation { MainScreen(onLogout = it) }
}
