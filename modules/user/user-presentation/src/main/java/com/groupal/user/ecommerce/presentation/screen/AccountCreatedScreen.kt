package com.groupal.user.ecommerce.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.groupal.shared.ecommerce.presentation.components.GradientButton
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme
import com.groupal.user_presentation.R


@Composable
fun AccountCreatedScreen(onSignIn: () -> Unit) {
    AccountCreatedContent(onSignIn = onSignIn)
}

@Composable
fun AccountCreatedContent(onSignIn: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(LocalTheme.current.space.xlarge))

        Image(
            modifier = Modifier
                .width(LocalTheme.current.width.medium)
                .height(LocalTheme.current.height.medium),
            painter = painterResource(id = R.drawable.ic_check_circle),
            contentDescription = stringResource(R.string.user_ic_success_accountcreated)
        )
        Spacer(modifier = Modifier.height(LocalTheme.current.height.small))

        Text(
            fontSize = LocalTheme.current.fontSize.medium,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            text = stringResource(R.string.user_success_accountcreated)
        )

        Spacer(modifier = Modifier.height(LocalTheme.current.height.xlarge))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(LocalTheme.current.height.xlarge))

            Text(
                fontSize = LocalTheme.current.fontSize.small,
                color = Color.Gray,
                text = stringResource(R.string.user_congrat_accountcreated)
            )

            Spacer(modifier = Modifier.height(LocalTheme.current.space.xlarge))

            GradientButton(
                text = stringResource(R.string.user_btn_go_to_login_accountcreated),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onClick = {onSignIn() },
                icon = R.drawable.ic_login_button_arrow
            )
        }
    }
}

