package com.groupal.shared.ecommerce.presentation.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily

@Composable
fun ErrorText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = MaterialTheme.colors.error,
        fontFamily = FontFamily.Monospace,
        style = MaterialTheme.typography.caption,
        modifier =  modifier
    )
}