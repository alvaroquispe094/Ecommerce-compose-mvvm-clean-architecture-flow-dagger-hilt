package com.groupal.shared.ecommerce.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalNavController = compositionLocalOf<NavController?> { null }

@Composable
fun currentNavController() = LocalNavController.current
