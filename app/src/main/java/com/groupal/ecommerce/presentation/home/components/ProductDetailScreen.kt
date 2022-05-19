package com.groupal.ecommerce.presentation.home.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow
import com.groupal.ecommerce.R
import com.groupal.ecommerce.presentation.home.HomeScreenState
import com.groupal.ecommerce.presentation.home.HomeViewModel
import com.groupal.ecommerce.presentation.theme.DarkGray
import com.groupal.ecommerce.presentation.theme.Purple500
import com.groupal.ecommerce.presentation.theme.White

@Composable
fun ProductDetailScreen (
    openDrawer: () -> Unit,
    homeUiState: HomeScreenState,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Color(0xFFFFFFFF),
        topBar = {
            TopAppBar(
                backgroundColor  = White,
                modifier = Modifier.border(BorderStroke(0.dp,Color.White))
                    .shadow(0.dp)
            ){
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.high
                ) {
                    IconButton(onClick = {homeViewModel.interactedWithFeed()}) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "Volver",tint = Purple500)
                    }
                }

                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.high,
                    LocalTextStyle provides MaterialTheme.typography.h6
                ) {
                    Text(

                        color = DarkGray,
                        text = "Details",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }

            }
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(contentPadding)
        ) {
            homeUiState.product?.let { product ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    item {
                        AsyncImage(
                            model = product.image,
                            contentDescription = stringResource(R.string.interests_title),
                            modifier = Modifier.align(Alignment.Center)
                                .fillMaxSize()
                        )

                        Spacer(modifier = Modifier.height(15.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${product.title})",
                                style = MaterialTheme.typography.h3,
                                modifier = Modifier.weight(8f)
                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "$${product.price}",
                            style = MaterialTheme.typography.subtitle1
                        )

                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = product.description,
                            style = MaterialTheme.typography.body2
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Categories",
                            style = MaterialTheme.typography.h4
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        FlowRow(
                            mainAxisSpacing = 10.dp,
                            crossAxisSpacing = 10.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colors.primary,
                                        shape = RoundedCornerShape(100.dp)
                                    )
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = product.category,
                                    color = MaterialTheme.colors.primary,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.body2
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Rating",
                            style = MaterialTheme.typography.h4
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        FlowRow(
                            mainAxisSpacing = 10.dp,
                            crossAxisSpacing = 10.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colors.primary,
                                        shape = RoundedCornerShape(100.dp)
                                    )
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = "${product.rating.rate}",
                                    color = MaterialTheme.colors.primary,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.body2
                                )
                            }
                        }
                    }
//                items(coin.team) { teamMember ->
//                    TeamListItem(
//                        teamMember = teamMember,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(10.dp)
//                    )
//                    Divider()
//                }
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
        BackHandler {
            homeViewModel.interactedWithFeed()
        }
    }

}