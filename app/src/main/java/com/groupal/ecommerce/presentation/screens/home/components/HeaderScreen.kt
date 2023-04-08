package com.groupal.ecommerce.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.groupal.ecommerce.R
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme

@Composable
fun HeaderScreen(
    navigateToLogin: () -> Unit,
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        //backgroundColor  = White,
        modifier = Modifier
            //.border(BorderStroke(1.dp, White))
            //.shadow(LocalTheme.current.padding.xxsmall)
    ){
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.high
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_dark),
                contentDescription = stringResource(R.string.logo_description)
            )
        }

        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.high,
            LocalTextStyle provides MaterialTheme.typography.h6
        ) {
            Text(
                text = stringResource(R.string.app_name_marketplace),
                fontSize = LocalTheme.current.fontSize.large,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }

        IconButton(
                onClick = { /*TODO*/ }
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = stringResource(R.string.logo_description),
            )
        }

    }
}