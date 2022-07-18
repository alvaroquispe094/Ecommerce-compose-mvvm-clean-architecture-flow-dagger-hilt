package com.groupal.ecommerce.presentation.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.groupal.ecommerce.presentation.theme.Purple700

@Composable
fun LoginScreen(
    loginUiState: LoginUiState,
    loginViewModel: LoginViewModel,
    navigateToHome: () -> Unit,
    navigateToSignup: () -> Unit,
){
    val text = buildAnnotatedString {
        append("Don't have an account?")
        pushStringAnnotation("URL", "https://www.develou.com")
        withStyle(
            SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        ) {
            append(" Sign Up")
        }
        pop()
    }

    Box(modifier = Modifier.fillMaxSize()) {
    ClickableText(
        text = text,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(20.dp),
        onClick = { navigateToSignup() },
        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            textDecoration = TextDecoration.Underline,
            color = Purple700
        )
    )
    }
    Column(
    modifier = Modifier.padding(20.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(loginUiState.user) }
        val password = remember { mutableStateOf(loginUiState.password) }

        Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { loginViewModel.login(username.value, password.value)},
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Forgot password?"),
            onClick = { },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default
            )
        )
    }
}