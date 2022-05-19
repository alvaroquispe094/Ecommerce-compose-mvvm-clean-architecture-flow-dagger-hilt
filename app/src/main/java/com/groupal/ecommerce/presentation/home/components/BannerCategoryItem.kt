package com.groupal.ecommerce.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.groupal.ecommerce.R
import com.groupal.ecommerce.domain.model.Category
import com.groupal.ecommerce.presentation.home.HomeViewModel

@Composable
fun BannerCategoryItem(
    category: Category,
    homeViewModel: HomeViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { /*homeViewModel.selectProduct(product)*/ })
            .padding(8.dp, 8.dp)
            .size(70.dp, 70.dp),
//            .shadow(elevation = 0.5.dp),
            shape = MaterialTheme.shapes.medium,




//        verticalArrangement = Arrangement.SpaceBetween
    ) {
//        Image(
//            painter = rememberAsyncImagePainter(category.path),
//            contentDescription = stringResource(R.string.interests_title),
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//                .size(65.dp, 65.dp)
//                .weight(0.5f)
//        )
        AsyncImage(
            model = category.path,
            contentDescription = stringResource(R.string.interests_title),
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//                .size(70.dp, 70.dp)
//                .weight(0.5f)
        )
//        Text(
//            text = "${category.category}",
//            style = MaterialTheme.typography.body1,
//            overflow = TextOverflow.Ellipsis,
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//                .weight(0.5f),
//            textAlign = TextAlign.Center,
//        )

    }

}