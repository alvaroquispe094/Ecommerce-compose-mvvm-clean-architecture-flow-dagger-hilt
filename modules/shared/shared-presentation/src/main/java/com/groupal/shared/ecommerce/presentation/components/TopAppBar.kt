package com.groupal.shared.ecommerce.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme
import com.groupal.shared.presentation.R

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    shouldShowBack: Boolean = true,
    textTitle: String? = null,
    onBack: () -> Unit = {}
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = modifier
    ) {
        if (shouldShowBack){
            IconButton(
                modifier = modifier.alpha(if (shouldShowBack) 1f else 0f),
                enabled = shouldShowBack,
                onClick = onBack
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "",
                )
            }
        }

        if (textTitle != null) {
            Text(text = textTitle, color = Color.Black, fontSize = 20.sp)
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(LocalTheme.current.space.small))

            Image(
                painter = painterResource(id = R.drawable.ic_logo_name),
                contentDescription = ""
            )
        }


    }
}

@Composable
@Preview(showBackground = true)
private fun TopAppBarPreview() {
    TopAppBar(textTitle = "")
}

@Composable
@Preview(showBackground = true)
private fun TopAppBarPreviewNoBack() {
    TopAppBar(
        shouldShowBack = false, textTitle = "Detalle de compra"
    )
}

@Composable
@Preview(showBackground = true)
private fun TopAppBarPreviewLogo() {
    TopAppBar(
        shouldShowBack = false, textTitle = null
    )
}