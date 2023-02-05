package com.groupal.user.ecommerce.presentation.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.groupal.shared.ecommerce.presentation.components.*
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme
import com.groupal.user.ecommerce.domain.User
import com.groupal.user.ecommerce.domain.UserValidation
import com.groupal.user.ecommerce.presentation.viewmodel.AuthViewModel
import com.groupal.user_presentation.R
import kotlinx.coroutines.launch


//statefull
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    onSignUp: () -> Unit,
) {
    val context = LocalContext.current
    val errorMessage = (stringResource(com.groupal.shared.R.string.invalid_field))

    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf<String?>(null) }

    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf<String?>(null) }

    val isLoginButtonEnable =
        (emailError == null && passwordError == null && email.isNotEmpty() && password.isNotEmpty())

    val loginError by authViewModel.loginError.collectAsState()
    val loginLoading by authViewModel.loginLoading.collectAsState()
    val coroutineScope = rememberCoroutineScope() //Scope para coroutines en composables

//    val isSignUpEnabled by authViewModel.isSignUpEnabled.collectAsState()

    LaunchedEffect(loginError) { //TODO: No hacer efectos sobre el que los esta generando
        if (loginError != null) {
            Toast.makeText(context, loginError.toString(), Toast.LENGTH_LONG).show()
            authViewModel.cleanLoginError()
        }
    }

    LoginContent(
        //TODO: nunca ejecutar corrotuinas en UI, utilizando otra cosa que no sea un efectos (por ej launchedEfects) o callbacks (aunque tambien se considere un efecto)
        email = email,
        emailError = emailError,
        password = password,
        passwordError = passwordError,
        signInLoading = loginLoading,
        onSignIn = {
            coroutineScope.launch {
                authViewModel.login(
                    email,
                    password
                ) //TODO: Si no cambio el Scope dentro del viewmodel se ejecuta en el scope de composables
            }
        },
        onSignUp = onSignUp,
        onChangeEmail = {
            email = it
            emailError =
                if (User.isEmailValid(it)) null else UserValidation.emailValidationMessage(
                    context
                )
        },
        onChangePassword = {
            password = it
            passwordError =
                if (User.isPasswordValid(it)) null else UserValidation.passwordAndLongValidationMessage(
                    8,
                    20,
                    context)

        },
        onBlurEmail = {
            emailError =
                if (User.isEmailValid(it)) null else UserValidation.emailValidationMessage(
                    context
                )
        },
        onBlurPassword = {
            passwordError =
                if (User.isPasswordValid(it)) null else UserValidation.passwordAndLongValidationMessage(
                    8,
                    20,
                    context
                )
        },
        isLoginButtonEnable = isLoginButtonEnable,
        isSignUpEnabled = true
    )
}

//stateless
@Composable
private fun LoginContent(
    //TODO: EL contenido no deberia conocer al viewmodel, ni ejecutar coroutines
    email: String,
    emailError: String?,
    password: String,
    passwordError: String?,
    onSignIn: () -> Unit,
    onSignUp: () -> Unit,
    signInLoading: Boolean,
    onChangeEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onBlurEmail: (String) -> Unit,
    onBlurPassword: (String) -> Unit,
    isLoginButtonEnable: Boolean,
    isSignUpEnabled: Boolean,

    ) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = LocalTheme.current.padding.xxlarge)
            .then(
                if (signInLoading) Modifier.alpha(LocalTheme.current.alpha.small)
                else Modifier.alpha(LocalTheme.current.alpha.medium)
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
        ) {

            Spacer(modifier = Modifier.weight(1f))

            TextTitle(
                text = stringResource(R.string.user_title_login),
                modifier = Modifier
                    .align(Alignment.Start)
            )
            TextSubTitle(
                text = stringResource(R.string.user_subtitle_login),
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(LocalTheme.current.space.xxlarge))
            TextFieldGeneric(
                value = email,
                errorValidator = emailError,
                onValueChange = { onChangeEmail(it) },
                onFocusChange = { onBlurEmail(it) },
                text = stringResource(R.string.user_username),
                iconId = R.drawable.ic_login_email,
                iconDescription = stringResource(R.string.user_ic_desc_mail_login)
            )

            Spacer(modifier = Modifier.height(LocalTheme.current.space.xsmall))

            TextFieldPassword(
                value = password,
                errorValidator = passwordError,
                onValueChange = { onChangePassword(it) },
                onFocusChange = { onBlurPassword(it) },
                text = stringResource(R.string.user_password),
                iconId = R.drawable.ic_login_password,
                iconDescription = stringResource(R.string.user_ic_desc_login_pass),
            )
            Spacer(modifier = Modifier.height(LocalTheme.current.space.xsmall))

            TextClickable(
                modifier = Modifier
                    .align(Alignment.End),
                text = stringResource(R.string.user_forgot_login),
                onClick = {
                    recoverPass()
                }
            )

            Spacer(
                modifier = Modifier
                    .height(LocalTheme.current.space.large)
            )
            Button(
                text = stringResource(R.string.user_btn_login),
                color = Color(0xFF2E31E4),
                modifier = Modifier
                    .align(Alignment.End),
                onClick = { onSignIn() },
                isEnable = isLoginButtonEnable && !signInLoading,
                icon = R.drawable.ic_login_button_arrow
            )

            Spacer(modifier = Modifier.weight(1f))

//            if (isSignUpEnabled) {
                Row(Modifier.padding(bottom = LocalTheme.current.padding.xlarge)) {
                    TextBody(
                        text = stringResource(R.string.user_body_sign_up)
                    )

                    TextClickable(
                        text = stringResource(R.string.user_sign_up),
                        onClick = { onSignUp() }
                    )
                }
//            }

        }
        if (signInLoading) {
            UsersLoadingProgressIndicator()
        }
    }
}

fun recoverPass() {
    Log.d("Forgot", "Password Recovery was pressed")
}


@Composable
private fun UsersLoadingProgressIndicator() {
    Box(modifier = Modifier.fillMaxWidth()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}