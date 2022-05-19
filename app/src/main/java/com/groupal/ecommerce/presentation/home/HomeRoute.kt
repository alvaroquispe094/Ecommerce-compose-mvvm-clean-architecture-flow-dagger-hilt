package com.groupal.ecommerce.presentation.home

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
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
    openDrawer: () -> Unit,
) {
    // UiState of the HomeScreen, escucha todo los que se actualize en state y redibuja todo a partir de aquís
    val state by homeViewModel.state.collectAsState()

    //actualiza los productos
//    homeViewModel.getProducts()
//    homeViewModel.getCategories()

    // pasar variable para mantener el estado del scroll de la lista al volver
    val homeListLazyListState = rememberLazyListState()
    when (getHomeScreenType(isExpandedScreen, state)) {
        //pantalla principal de home
        HomeScreenEnum.Feed -> {
            check(true)
            HomeScreen(openDrawer,state,homeViewModel, homeListLazyListState)
        }
        //pantalla de detalle de producto seleccionado
        HomeScreenEnum.ArticleDetails -> {
            check(true)
            ProductDetailScreen(openDrawer,state)
        }

        else -> {
            check(true)
            HomeScreen(openDrawer,state,homeViewModel,homeListLazyListState)
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
    //si no esta en landscape
    false -> {
        if (state.isProductOpen) {
            HomeScreenEnum.ArticleDetails
        } else {
            HomeScreenEnum.Feed
        }
    }// si esta en landscape, cambiar logica para visualizar de otra manera
    true -> {
        if (state.isProductOpen) {
            HomeScreenEnum.ArticleDetails
        } else {
            HomeScreenEnum.Feed
        }
    }

}
