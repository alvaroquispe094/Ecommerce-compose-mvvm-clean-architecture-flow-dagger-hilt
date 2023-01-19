package com.groupal.ecommerce.presentation.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.groupal.ecommerce.presentation.theme.Purple500

@Composable
fun SearchScreen(
    navigateToLogin: () -> Unit,
){

    TopAppBar(
        backgroundColor  = White,
        modifier = Modifier
            .border(BorderStroke(0.dp, Color.White))
            .shadow(0.dp)
    ){
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.high
        ) {
            //logout button
            TextButton(
                onClick = { navigateToLogin() },
                border = BorderStroke(width = 5.dp, color = Color.Green)
            ) {
                Text(text = "Logout")
            }
        }

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