package com.groupal.shared.ecommerce.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme

@Composable
fun GradientButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    @DrawableRes icon: Int,
    onClick: () -> Unit = { }
) {
    Button(
        modifier = modifier
            .focusable()
            .onFocusChanged {}
            .then(
                if (!isEnable) Modifier.alpha(0.5F)
                else Modifier.alpha(1.0F)
            ),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        elevation = ButtonDefaults.elevation(
            defaultElevation = LocalTheme.current.button.elevation.default,
            pressedElevation = LocalTheme.current.button.elevation.pressed,
            disabledElevation = LocalTheme.current.button.elevation.disabled
        ),
        shape = RoundedCornerShape(50),
        onClick = { onClick() },
        enabled = isEnable
    ) {
        Box(
            modifier = Modifier
                .background(Brush.horizontalGradient(listOf(Color(0xFF3B4FCF), Color(0xFF2234CF))))
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier.padding(LocalTheme.current.padding.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    fontSize = LocalTheme.current.fontSize.xsmall,
                    color = Color.White,
                    modifier = Modifier.padding(LocalTheme.current.padding.xsmall)
                )
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Arrow Login Button",
                    tint = Color.White,
                    modifier = Modifier
                        .requiredHeight(LocalTheme.current.requiredHeight.medium)
                        .requiredWidth(LocalTheme.current.requiredWidth.medium)
                )
            }
        }
    }
}