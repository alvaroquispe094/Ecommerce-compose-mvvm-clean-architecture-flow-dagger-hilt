package com.groupal.shared.ecommerce.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.DialogProperties
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme

@Composable
fun Dialog(content: @Composable () -> Unit){
    // Dialog state Manager
    val dialogState: MutableState<Boolean> = remember { mutableStateOf(false) }
    if (dialogState.value) {
        val dialog = androidx.compose.ui.window.Dialog(
            onDismissRequest = { dialogState.value = false },
            content = { content() },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}

@Composable
fun CompleteDialogContent(
    title: String,
    successButtonText: String,
    iconContentDescription: String,
    dialogState: MutableState<Boolean>,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxHeight(LocalTheme.current.fillMaxHeight.small)
            .fillMaxWidth(LocalTheme.current.fillMaxWidth.medium),
        shape = RoundedCornerShape(LocalTheme.current.button.roundedCornerShape.small)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(LocalTheme.current.fillMaxWidth.medium)
                .fillMaxHeight(LocalTheme.current.fillMaxHeight.medium),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TitleAndButton(title, iconContentDescription, dialogState)
            AddBody(content)
            BottomButtons(successButtonText, dialogState = dialogState)
        }
    }
}

@Composable
fun BodyContent(
    text: String
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontSize = LocalTheme.current.fontSize.small
    )
}

@Composable
private fun TitleAndButton(title: String, iconContentDescription: String, dialogState: MutableState<Boolean>) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(LocalTheme.current.fillMaxWidth.medium)
                .padding(LocalTheme.current.padding.large),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = LocalTheme.current.fontSize.large)
            IconButton(modifier = Modifier.then(Modifier.size(LocalTheme.current.size.small)),
                onClick = {
                    dialogState.value = false
                }) {
                Icon(
                    Icons.Filled.Close,
                    iconContentDescription
                )
            }
        }
        Divider(color = Color.DarkGray, thickness = LocalTheme.current.thickness.medium)
    }
}

@Composable
private fun BottomButtons(successButtonText: String, dialogState: MutableState<Boolean>) {
    Row(
        modifier = Modifier
            .fillMaxWidth(LocalTheme.current.fillMaxWidth.medium)
            .padding(LocalTheme.current.padding.large),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                dialogState.value = false
            },
            modifier = Modifier.width(LocalTheme.current.width.medium),
            shape = RoundedCornerShape(LocalTheme.current.button.roundedCornerShape.medium)
        ) {
            Text(text = successButtonText, fontSize = LocalTheme.current.fontSize.medium)
        }

    }
}

@Composable
private fun AddBody(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .padding(LocalTheme.current.padding.medium)
    ) {
        content()
    }
}