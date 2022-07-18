package com.groupal.ecommerce.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.groupal.ecommerce.presentation.home.components.HomeContentScreen
import com.groupal.ecommerce.presentation.components.TopBarNavigation

@Composable
fun HomeScreen(
    homeUiState: HomeScreenState,
    homeViewModel: HomeViewModel,
    homeListLazyListState: LazyListState,
    navigateToLogin: () -> Unit,
    scaffoldState: ScaffoldState,
) {
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Color(0xFFFFFFFF),
        topBar = {
            // top bar nav screen
            TopBarNavigation(
                navigateToLogin,
            )
        }
    ) {contentPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(contentPadding),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            //Screen home content
            HomeContentScreen(homeUiState, homeViewModel, homeListLazyListState)
        }
    }
}