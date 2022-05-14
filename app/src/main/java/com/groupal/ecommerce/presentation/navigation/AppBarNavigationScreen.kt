package com.groupal.ecommerce.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AppBarNavigation() {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar {
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Abrir menú")
                        }
                    }

                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                        LocalTextStyle provides MaterialTheme.typography.h6
                    ) {
                        Text(
                            text = "Ecommerce",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        )
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = "Ajustes")
                    }
                }
            }
        ) {
                contentPadding ->
            // Screen content
            Box(modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
            ) {
                Text(
                    text = "Contenido",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}