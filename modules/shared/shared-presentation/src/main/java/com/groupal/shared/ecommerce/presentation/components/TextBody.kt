package com.groupal.shared.ecommerce.presentation.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme

@Composable
fun TextBody(
    modifier: Modifier = Modifier,
    text: String
){
    Text(
        modifier= modifier,
        color = Color.Gray,
        fontSize = LocalTheme.current.fontSize.xsmall,
        fontWeight = FontWeight.W600,
        text = text
    )
}