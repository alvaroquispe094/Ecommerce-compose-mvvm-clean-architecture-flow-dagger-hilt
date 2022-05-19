package com.groupal.ecommerce.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.groupal.ecommerce.common.enums.HomeScreenEnum
import com.groupal.ecommerce.presentation.home.components.ProductDetailScreen


@Composable
fun HomeRoute(
    isExpandedScreen: Boolean,
    homeViewModel: HomeViewModel = hiltViewModel(),
    openDrawer: () -> Unit
) {
    // UiState of the HomeScreen, escucha todo los que se actualize en state y redibuja todo a partir de aquís
    val state by homeViewModel.state.collectAsState()

    //actualiza los productos
//    homeViewModel.getProducts()

//    homeViewModel.getCategories()

    when (getHomeScreenType(isExpandedScreen, state)) {
        //pantalla principal de home
        HomeScreenEnum.Feed -> {
            HomeScreen(openDrawer,state,homeViewModel)
        }
        //pantalla de detalle de producto seleccionado
        HomeScreenEnum.ArticleDetails -> {
            ProductDetailScreen(openDrawer,state)
        }

        else -> {
            HomeScreen(openDrawer,state,homeViewModel)
        }
    }
}

/**
 * Returns the current [HomeScreenType] to display, based on whether or not the screen is expanded
 * and the [HomeUiState].
 */
@Composable
private fun getHomeScreenType(
    isExpandedScreen: Boolean,
    state: HomeScreenState
): HomeScreenEnum = when (isExpandedScreen) {
    false -> {
        if (state.isProductOpen) {
            HomeScreenEnum.ArticleDetails
        } else {
            HomeScreenEnum.Feed
        }
    }
    true -> {
        if (state.isProductOpen) {
            HomeScreenEnum.ArticleDetails
        } else {
            HomeScreenEnum.Feed
        }
    }

}
