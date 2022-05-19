package com.groupal.ecommerce.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.groupal.ecommerce.domain.model.Product
import com.groupal.ecommerce.R
import com.groupal.ecommerce.presentation.home.HomeScreenState
import com.groupal.ecommerce.presentation.home.HomeViewModel
import com.groupal.ecommerce.presentation.theme.DarkGray

@Composable
fun HomeListItem(
    product: Product,
    homeViewModel: HomeViewModel
//    onItemClick: (HomeScreenState) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { homeViewModel.selectProduct(product) })
            .padding(16.dp,30.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
//        Image(
//            painter = rememberAsyncImagePainter(product.image),
//            contentDescription = stringResource(R.string.interests_title),
//            modifier = Modifier.align(Alignment.CenterVertically)
//                .size(65.dp, 65.dp)
//                .weight(0.5f)
//        )
        AsyncImage(

            model = product.image,
            contentDescription = stringResource(R.string.interests_title),
            modifier = Modifier.align(Alignment.CenterVertically)
                                .size(65.dp, 65.dp)
                                .weight(0.5f)
        )
        Text(
            text = "${product.title}",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(Alignment.CenterVertically)
                                .weight(2f),
            textAlign = TextAlign.Center,
        )
        Text(
            text = "$${product.price}",
            style = MaterialTheme.typography.body2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(Alignment.CenterVertically)
                                .weight(0.5f),
            textAlign = TextAlign.End,
        )
    }
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )

}