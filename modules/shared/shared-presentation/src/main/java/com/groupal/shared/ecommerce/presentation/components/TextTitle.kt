package com.groupal.shared.ecommerce.presentation.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme

@Composable
fun TextTitle(
    modifier: Modifier = Modifier,
    text: String
){
    Text(
        modifier = modifier,
        text = text,
        fontSize = LocalTheme.current.fontSize.xlarge,
        fontWeight = FontWeight.W800,
        textAlign = TextAlign.Center
    )
}