package com.groupal.shared.ecommerce.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme

@Composable
fun SearchScreen(
    value: String,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalTheme.current.padding.medium)
    ){
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF)),
            shape = RoundedCornerShape(30),
            value = value,
            onValueChange = { onValueChange(it) },
            leadingIcon = {
                IconButton( onClick = { /*Metodo para Realizar la busqueda*/ } ){
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black.copy(
                            alpha = ContentAlpha.medium
                        )
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    onValueChange("")
                    focusManager.clearFocus()
                }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Icon",
                        tint = Color.Black
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.LightGray.copy(
                    alpha = ContentAlpha.medium
                ),
                focusedBorderColor = Color.LightGray.copy(
                    alpha = ContentAlpha.medium
                ),

            )
        )
    }

}