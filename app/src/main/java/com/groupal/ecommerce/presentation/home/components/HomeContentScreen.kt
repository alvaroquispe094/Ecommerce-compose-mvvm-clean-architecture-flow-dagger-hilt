package com.groupal.ecommerce.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.groupal.ecommerce.presentation.home.HomeScreenState
import com.groupal.ecommerce.presentation.home.HomeViewModel

@Composable
fun HomeContentScreen(
    homeUiState: HomeScreenState,
    homeViewModel: HomeViewModel,
    state: LazyListState = rememberLazyListState(),
){
    // Screen home content
    Box(modifier = Modifier
        .padding(8.dp)) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = state
        ) {
            //agrega un item al principio antes de dibujar la lista de items
            item {
                Text(
                    text = "Categories",
                    style = MaterialTheme.typography.h5,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(16.dp,0.dp),
//                .weight(0.5f),
                    textAlign = TextAlign.Left,

                    )
            }
            item {
                BannerCategoryScreen(homeUiState = homeUiState, homeViewModel = homeViewModel)
            }
            item {
                Text(
                    text = "Products",
                    style = MaterialTheme.typography.h5,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(16.dp,0.dp),
//                .weight(0.5f),
                    textAlign = TextAlign.Left,
                    )
            }
            // Usar esto en lugar de items porque funciona lento
            item {
                Column {
                    homeUiState.products.forEach { product ->
                        HomeListItem(
                            product = product,
                            homeViewModel = homeViewModel
                        )
//                        PostListDivider()
                    }
                }
            }


        }
        if (homeUiState.error.isNotBlank()) {
            Text(
                text = homeUiState.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (homeUiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}