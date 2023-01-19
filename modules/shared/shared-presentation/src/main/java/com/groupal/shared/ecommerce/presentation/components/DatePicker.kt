package com.groupal.shared.ecommerce.presentation.components

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme
import java.util.Calendar
import java.util.Date

@Composable
fun DatePicker(
    context: Context = LocalContext.current,
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    conId: Int,
    iconDescription: String,
){

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val monthNormalize = month + 1
            date.value = "$dayOfMonth/$monthNormalize/$year"
            onValueChange(date.value)
        }, year, month, day
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            enabled = false,
            value =  value,
            readOnly = true,
            onValueChange = { },
            textStyle = TextStyle(
                fontSize = LocalTheme.current.fontSize.small,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(LocalTheme.current.shadow.medium)
                .clickable { datePickerDialog.show() },
            label = {
                Text(text = text, fontWeight = FontWeight.Bold)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = conId),
                    tint = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
                    contentDescription = iconDescription,
                    modifier = Modifier
                        .requiredHeight(LocalTheme.current.requiredHeight.medium)
                        .requiredWidth(LocalTheme.current.requiredWidth.medium)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current),
                disabledLabelColor =  MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
            ),
        )
    }

}