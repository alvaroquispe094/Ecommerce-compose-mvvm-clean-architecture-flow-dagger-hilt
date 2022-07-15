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
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    // UiState of the HomeScreen, escucha todo los que se actualize en state y redibuja todo a partir de aquí
    val state by homeViewModel.state.collectAsState()

    //actualiza los productos
//    homeViewModel.getProducts()
//    homeViewModel.getCategories()

    // pasar variable para mantener el estado del scroll de la lista al volver
    val homeListLazyListState = rememberLazyListState()
    when (getHomeScreenType(state)) {
        //pantalla principal de home
        HomeScreenEnum.Feed -> {
            HomeScreen(state,homeViewModel, homeListLazyListState)
        }
        //pantalla de detalle de producto seleccionado
        HomeScreenEnum.ArticleDetails -> {
            ProductDetailScreen(state, homeViewModel)
        }

        else -> {
            HomeScreen(state,homeViewModel,homeListLazyListState)
        }
    }
}

/**
 * Returns the current [HomeScreenType] to display, based on whether or not the screen is expanded
 * and the [HomeUiState].
 */
@Composable
private fun getHomeScreenType(
    state: HomeScreenState
): HomeScreenEnum{

    return if (state.isProductOpen) {
        HomeScreenEnum.ArticleDetails
    } else {
        HomeScreenEnum.Feed
    }


}
