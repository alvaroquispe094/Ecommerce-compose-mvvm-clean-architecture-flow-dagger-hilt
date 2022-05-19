package com.groupal.ecommerce.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
fun BannerCategoryScreen(
    homeUiState: HomeScreenState,
    homeViewModel: HomeViewModel
){
    Box(modifier = Modifier
        .padding(8.dp,0.dp)
        .height(80.dp)
    ) {
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            item {
                Row {
                    homeUiState.categories.forEach { category ->
                        BannerCategoryItem(
                            category = category,
                            homeViewModel = homeViewModel
                        )
//                        PostListDivider()
                    }
                }
            }
        }
//        if (homeUiState.error.isNotBlank()) {
//            Text(
//                text = homeUiState.error,
//                color = MaterialTheme.colors.error,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .align(Alignment.Center)
//
//            )
//        }
//        if (homeUiState.isLoading) {
//            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//        }
    }

}