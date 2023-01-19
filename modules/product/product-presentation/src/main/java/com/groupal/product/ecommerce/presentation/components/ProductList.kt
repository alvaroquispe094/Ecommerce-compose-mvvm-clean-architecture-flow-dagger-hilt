package com.groupal.product.ecommerce.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.groupal.product.ecommerce.domain.Product
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme

@Composable
fun ProductList(
    products: List<Product>,
    state: LazyGridState = rememberLazyGridState(),
){
    // Product Grid on home screen
    Box(modifier = Modifier
        .padding(LocalTheme.current.padding.xxsmall)
    ) {

        LazyVerticalGrid(
            modifier = Modifier
                //.fillMaxSize()
                .navigationBarsPadding(),
            state = state,
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(LocalTheme.current.padding.xsmall)
        ){
            items(products) { ProductCard(it) }
        }
    }

}