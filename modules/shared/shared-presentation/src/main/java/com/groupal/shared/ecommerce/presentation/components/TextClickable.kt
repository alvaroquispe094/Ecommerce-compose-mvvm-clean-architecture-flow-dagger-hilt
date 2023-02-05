package com.groupal.shared.ecommerce.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TextClickable(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = { }
) {
    Text(
        text = text,
        color = Color(0xFF3B4FCF),
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .clickable(onClick = { onClick() })
    )
}