package com.groupal.shared.ecommerce.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme


@Composable
fun TextFieldGeneric(
    value: String,
    errorValidator: String? = null,
    onValueChange: (String) -> Unit,
    onFocusChange: (String) -> Unit,
    text: String,
    @DrawableRes iconId: Int,
    iconDescription: String
) {
    var isBlurred by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = TextStyle(
            fontSize = LocalTheme.current.fontSize.small,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier
            //.border(2.dp, color)
            .fillMaxWidth()
            .shadow(LocalTheme.current.shadow.medium)
            .onFocusChanged {
                if (it.isFocused && !isBlurred){
                    isBlurred = true
                }
                if (!it.isFocused && isBlurred){
                    onFocusChange(value)
                }
            },
        label = {
            Text(text = text, fontWeight = FontWeight.Bold)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = iconDescription,
                modifier = Modifier
                    .requiredHeight(LocalTheme.current.requiredHeight.medium)
                    .requiredWidth(LocalTheme.current.requiredWidth.medium)
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background
        ),
        isError = errorValidator != null
    )

    if (errorValidator != null) {
        ErrorText(text = errorValidator, modifier = Modifier
            .padding(start = LocalTheme.current.padding.medium)
            .fillMaxWidth())
    }
}