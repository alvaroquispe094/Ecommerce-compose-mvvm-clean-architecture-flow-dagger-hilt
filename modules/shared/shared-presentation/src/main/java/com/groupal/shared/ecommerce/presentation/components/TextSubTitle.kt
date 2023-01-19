package com.groupal.shared.ecommerce.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.groupal.shared.ecommerce.presentation.util.spDimensionResource
import com.groupal.shared.presentation.R

@Composable
fun TextSubTitle(
    modifier: Modifier = Modifier,
    text: String
){
    Text(
        text = text,
        fontSize = spDimensionResource(id = R.dimen.fontsize_medium),
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        modifier = modifier
            .padding(vertical = dimensionResource(id = R.dimen.padding_xsmall))
    )
}