package com.groupal.ecommerce.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.groupal.ecommerce.presentation.screens.home.components.HeaderScreen
import com.groupal.ecommerce.presentation.viewmodel.HomeViewModel
import com.groupal.product.ecommerce.domain.Product
import com.groupal.product.ecommerce.presentation.components.ProductList
import com.groupal.shared.ecommerce.presentation.components.TopAppBar
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
){
    val products by homeViewModel.products.collectAsState()

    val homeLoading by homeViewModel.homeLoading.collectAsState()

    val scaffoldState = rememberScaffoldState()
    HomeContent(
        products = products,
        navigateToLogin = navigateToLogin,
        homeLoading = homeLoading,
        scaffoldState = scaffoldState
    )
}

@Composable
private fun HomeContent(
    products: List<Product>,
    navigateToLogin: () -> Unit,
    homeLoading: Boolean,
    scaffoldState: ScaffoldState,
){

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Color(0xFFFFFFFF),
        modifier = Modifier.padding(8.dp, 0.dp),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                shouldShowBack = false
            )
//            HeaderScreen(
//                navigateToLogin,
//            )
        },
        content = { paddingValues ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .then(
                        if (homeLoading) Modifier.alpha(LocalTheme.current.alpha.small)
                        else Modifier.alpha(LocalTheme.current.alpha.medium)
                    )
            ){
                Column(
                    modifier = Modifier
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    ProductList(products)
                }

                if (homeLoading) {
                    UsersLoadingProgressIndicator()
                }
            }
        }
    )
}

@Composable
private fun UsersLoadingProgressIndicator() {
    Box(modifier = Modifier.fillMaxWidth()) {
    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}