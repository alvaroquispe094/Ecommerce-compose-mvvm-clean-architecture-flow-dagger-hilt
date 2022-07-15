package com.groupal.ecommerce.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.groupal.ecommerce.presentation.theme.DarkGray
import com.groupal.ecommerce.presentation.theme.Purple500
import com.groupal.ecommerce.presentation.theme.White

@Composable
fun TopBarNavigation(
//    openDrawer: () -> Unit
) {
    TopAppBar(
        backgroundColor  = White,
        modifier = Modifier
            .border(BorderStroke(0.dp, Color.White))
            .shadow(0.dp)
    ){
//        CompositionLocalProvider(
//            LocalContentAlpha provides ContentAlpha.high
//        ) {
//            IconButton(onClick = openDrawer) {
//                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Abrir menú",tint = Purple500)
//            }
//        }

        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.high,
            LocalTextStyle provides MaterialTheme.typography.h6
        ) {
            Text(

                color = DarkGray,
                text = "Ecommerce",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        IconButton(onClick = { /*TODO*/ }) {

            Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Ajustes",tint = Purple500)
        }
    }
}