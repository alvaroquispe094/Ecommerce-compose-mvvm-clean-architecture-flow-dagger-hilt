package com.groupal.ecommerce.presentation.auth.register

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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.groupal.ecommerce.presentation.theme.Purple700

@Composable
fun SignupScreen(
    registerUiState: SignupUiState,
    registerViewModel: SignupViewModel,
    navigateToLogin: () -> Unit,
){
    Box(modifier = Modifier.fillMaxSize()) {
    ClickableText(
        text = AnnotatedString("Already have an account? Sign Up "),
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(20.dp),
        onClick = { navigateToLogin() },
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

        val name = remember { mutableStateOf(registerUiState.name) }
        val surname = remember { mutableStateOf(registerUiState.surname) }
        val username = remember { mutableStateOf(registerUiState.user) }
        val password = remember { mutableStateOf(registerUiState.password) }
        val address = remember { mutableStateOf(registerUiState.address) }

        Text(text = "Register", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Name") },
            value = name.value,
            onValueChange = { name.value = it })
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Surname") },
            value = surname.value,
            onValueChange = { surname.value = it })
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
        TextField(
            label = { Text(text = "Address") },
            value = address.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { address.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { registerViewModel.register(name.value,surname.value, username.value, password.value, address.value,)},
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Sign Up")
            }
        }

//        Spacer(modifier = Modifier.height(20.dp))
//        ClickableText(
//            text = AnnotatedString("Forgot password?"),
//            onClick = { },
//            style = TextStyle(
//                fontSize = 14.sp,
//                fontFamily = FontFamily.Default
//            )
//        )
    }
}