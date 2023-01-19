package com.groupal.product.ecommerce.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.groupal.product.ecommerce.domain.Product
import coil.compose.AsyncImage
import com.groupal.product.presentation.R
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme

@Composable
fun ProductCard(
    product: Product,
) {
    Card(
        modifier = Modifier.clickable { }
            .padding(4.dp)
    ) {
        Column() {

            AsyncImage(
                model = product.image,
                contentDescription = stringResource(R.string.product_description_image),

                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            product.name?.let {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(LocalTheme.current.padding.xxsmall),
                    text = it,
                    fontSize = LocalTheme.current.fontSize.xxsmall,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }

            product.price?.let {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = LocalTheme.current.padding.xsmall),
                    text = "$${product.price}",
                    fontSize = LocalTheme.current.fontSize.medium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}