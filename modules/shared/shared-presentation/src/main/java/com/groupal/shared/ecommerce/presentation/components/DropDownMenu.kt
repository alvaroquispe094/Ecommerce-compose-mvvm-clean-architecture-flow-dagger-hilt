package com.groupal.shared.ecommerce.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.toSize
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme

@Composable
fun DropDownMenu( //TODO: Generar validator para dropdown
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    conId: Int,
    iconDescription: String,
    options: List<String>,
){
    var expanded by remember { mutableStateOf(false) }
    //val suggestions = options
    //var selectedText by remember { mutableStateOf("") }

    var textFieldSize by remember { mutableStateOf(Size.Zero)}

    /*
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    */
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { expanded = !expanded } ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            enabled = false,
            value = value,
            onValueChange = { /*selectedText = it*/ },
            textStyle = TextStyle(
                fontSize = LocalTheme.current.fontSize.small,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(LocalTheme.current.shadow.medium)
                .clickable { expanded = !expanded }
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
                },
            label = {
                Text(text = text, fontWeight = FontWeight.Bold)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = conId),
                    contentDescription = iconDescription,
                    tint = colors.onSurface.copy(ContentAlpha.medium),
                    modifier = Modifier
                        .requiredHeight(LocalTheme.current.requiredHeight.medium)
                        .requiredWidth(LocalTheme.current.requiredWidth.medium)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colors.background,
                disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current),
                disabledLabelColor = colors.onSurface.copy(ContentAlpha.medium),
            ),
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ) {
            options.forEach { text ->
                DropdownMenuItem(onClick = {
                    //selectedText = label
                    onValueChange(text)
                    //gender = selectedText
                    expanded = false
                }) {
                    Text(text = text)
                }
            }
        }
    }
}