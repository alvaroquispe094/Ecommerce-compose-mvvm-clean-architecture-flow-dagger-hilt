package com.groupal.user.ecommerce.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.groupal.shared.ecommerce.presentation.components.GradientButton
import com.groupal.shared.ecommerce.presentation.components.TextFieldGeneric
import com.groupal.shared.ecommerce.presentation.components.TextFieldPassword
import com.groupal.shared.ecommerce.presentation.components.TextSubTitle
import com.groupal.shared.ecommerce.presentation.components.TextTitle
import com.groupal.shared.ecommerce.domain.Image
import com.groupal.shared.ecommerce.domain.SharedValidation
import com.groupal.shared.ecommerce.presentation.components.DatePicker
import com.groupal.shared.ecommerce.presentation.components.DropDownMenu
import com.groupal.shared.ecommerce.presentation.theme.LocalTheme
import com.groupal.user.ecommerce.domain.SignUpRequest
import com.groupal.user.ecommerce.domain.User
import com.groupal.user.ecommerce.domain.UserValidation
import com.groupal.user.ecommerce.presentation.viewmodel.AuthViewModel
import com.groupal.user_presentation.R
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    onSignIn: () -> Unit,
) {
    val context = LocalContext.current
    val errorMessage = (stringResource(com.groupal.shared.R.string.invalid_field))

    //val textSearch by authViewModel.emailSearch.collectAsState()
    //TextField(value = textSearch, onValueChange = viewModel::setSearchText)

    var firstName by rememberSaveable { mutableStateOf("") }
    var firstNameError by rememberSaveable { mutableStateOf<String?>(null) }

    var lastName by rememberSaveable { mutableStateOf("") }
    var lastNameError by rememberSaveable { mutableStateOf<String?>(null) }

    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf<String?>(null) }

    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf<String?>(null) }

    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var confirmPasswordError by rememberSaveable { mutableStateOf<String?>(null) }

    var gender by rememberSaveable { mutableStateOf("") }

    var birthDate by rememberSaveable { mutableStateOf("") }

    var image by rememberSaveable { mutableStateOf("") }
    var imageError by rememberSaveable { mutableStateOf<String?>(null) }

    var phone by rememberSaveable { mutableStateOf("") }
    var phoneError by rememberSaveable { mutableStateOf<String?>(null) }

    val isSignUpButtonEnable = (firstNameError == null &&
            lastNameError == null &&
            emailError == null &&
            passwordError == null &&
            confirmPasswordError == null &&
            imageError == null &&
            phoneError == null &&
            firstName.isNotEmpty() &&
            lastName.isNotEmpty() &&
            email.isNotEmpty() &&
            password.isNotEmpty() &&
            confirmPassword.isNotEmpty() &&
            gender.isNotEmpty() &&
            birthDate.isNotEmpty() &&
            image.isNotEmpty() &&
            phone.isNotEmpty()
            )

    val isConfirmPasswordEnable = (password.isNotEmpty() && passwordError == null)

    val signUpError by authViewModel.signUpError.collectAsState()
    val signUpLoading by authViewModel.signUpLoading.collectAsState()
    val userExist by authViewModel.userExist.collectAsState()
    val coroutineScope = rememberCoroutineScope() //Scope para coroutines en composables

    LaunchedEffect(signUpError) { //TODO: No hacer efectos sobre el que los esta generando
        if (signUpError != null) {
            Toast.makeText(context, signUpError.toString(), Toast.LENGTH_LONG).show()
            authViewModel.cleanSignUpError()
        }
    }

   LaunchedEffect(userExist) { //TODO: No hacer efectos sobre el que los esta generando
        emailError = if (userExist) UserValidation.userExistValidationMessage(context) else null
    }



    SignUpContent(
        //Attributes states
        firstName = firstName,
        firstNameError = firstNameError,
        lastName = lastName,
        lastNameError = lastNameError,
        email = email,
        emailError = emailError,
        password = password,
        passwordError = passwordError,
        confirmPassword = confirmPassword,
        confirmPasswordError = confirmPasswordError,
        gender = gender,
        birthDate = birthDate,
        image = image,
        imageError = imageError,
        phone = phone,
        phoneError = phoneError,
        signUpLoading = signUpLoading,
        //Methods success or fail login
        onSignIn = onSignIn,
        onSignUp = {
            coroutineScope.launch {
                authViewModel.signUp(
                    SignUpRequest(
                        null,
                        firstName,
                        lastName,
                        email,
                        password,
                        gender,
                        birthDate,
                        Image(image),
                        phone
                    )
                ) //TODO: Si no cambio el Scope dentro del viewmodel se ejecuta en el scope de composables
            }
        },
        //Methods to update states
        onChangeFirstname = {
            firstName = it
            firstNameError = if (User.isFirstnameValid(it)) null else UserValidation.lettersAndLongValidationMessage(2,30, context)
        },
        onChangeLastname = {
            lastName = it
            lastNameError = if (User.isLastnameValid(it)) null else UserValidation.lettersAndLongValidationMessage(2,30, context)
        },
        onChangeEmail = {
            email = it
            emailError = if (User.isEmailValid(email)) null else UserValidation.emailValidationMessage(context)
            //authViewModel.setSearchText(it)
            if(emailError == null){

                authViewModel.setSearchEmail(it)
            }

        },
        onChangePassword = {
            password = it
            passwordError = if (User.isPasswordValid(it)) null else UserValidation.passwordAndLongValidationMessage(8,20, context)
        },
        onChangeConfirmPassword = {
            confirmPassword = it
            confirmPasswordError = if (User.isConfirmPasswordValid(it, password)) null else UserValidation.confirmPasswordValidationMessage(context)
        },
        onChangeBirthDate = {
            birthDate = it
        },
        onChangeImage = {
            image = it
            imageError = if (User.isValidAvatar(it)) null else SharedValidation.imageValidationMessage(context)
        },
        onChangePhone = {
            phone = it
            phoneError = if (User.isPhoneValid(it)) null else UserValidation.phoneValidationMessage(context)
        },
        onChangeGender = {
            gender = it
        },
        onBlurFirstname = {
           firstNameError = if (User.isFirstnameValid(it)) null else UserValidation.lettersAndLongValidationMessage(2,30, context)
        },
        onBlurLastname = {
            lastNameError = if (User.isLastnameValid(it)) null else UserValidation.lettersAndLongValidationMessage(2,30, context)
        },
        onBlurEmail = {
            emailError = if (User.isEmailValid(it)) null else UserValidation.emailValidationMessage(context)
        },
        onBlurPassword = {
            passwordError = if (User.isPasswordValid(it)) null else UserValidation.passwordAndLongValidationMessage(8,20, context)
        },
        onBlurImage = {
            imageError = if (User.isValidAvatar(it)) null else SharedValidation.imageValidationMessage(context)
        },
        onBlurPhone = {
            phoneError = if (User.isPhoneValid(it)) null else UserValidation.phoneValidationMessage(context)
        },
        //Methods to update states errors

        isSignUpButtonEnable = isSignUpButtonEnable,
        isConfirmPasswordEnable = isConfirmPasswordEnable
    )
}


//stateless
@Composable
private fun SignUpContent( //TODO: Implementar clear all en inputs
    //CONTENT
    firstName: String,
    firstNameError: String?,
    lastName: String,
    lastNameError: String?,
    email: String,
    emailError: String?,
    password: String,
    passwordError: String?,
    confirmPassword: String,
    confirmPasswordError: String?,
    gender: String,
    birthDate: String,
    image: String,
    imageError: String?,
    phone: String,
    phoneError: String?,
    signUpLoading: Boolean,
    onSignIn: () -> Unit,
    onSignUp: () -> Unit,
    onChangeFirstname: (String) -> Unit,
    onChangeLastname: (String) -> Unit,
    onChangeEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onChangeConfirmPassword: (String) -> Unit,
    onChangeBirthDate: (String) -> Unit,
    onChangeGender: (String) -> Unit,
    onChangeImage: (String) -> Unit,
    onChangePhone: (String) -> Unit,
    onBlurFirstname: (String) -> Unit,
    onBlurLastname: (String) -> Unit,
    onBlurEmail: (String) -> Unit,
    onBlurPassword: (String) -> Unit,
    onBlurImage: (String) -> Unit,
    onBlurPhone: (String) -> Unit,
    isSignUpButtonEnable: Boolean,
    isConfirmPasswordEnable: Boolean
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = LocalTheme.current.padding.xxlarge)
            .then(
                if (signUpLoading) Modifier.alpha(LocalTheme.current.alpha.small)
                else Modifier.alpha(LocalTheme.current.alpha.medium)
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                ) {
                    Spacer(modifier = Modifier.height(LocalTheme.current.space.medium))

                    TextTitle(
                        text = stringResource(id = R.string.user_sign_up),
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    TextSubTitle(
                        text = stringResource(R.string.user_subtitle_signup),
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(LocalTheme.current.space.large))

                    TextFieldGeneric(
                        value = firstName,
                        errorValidator = firstNameError,
                        onValueChange = { onChangeFirstname(it) },
                        onFocusChange = { onBlurFirstname(it)},
                        text = stringResource(R.string.user_first_name_signup),
                        iconId = R.drawable.ic_first_name,
                        iconDescription = stringResource(R.string.user_ic_desc_first_name_signup)
                    )
                    Spacer(modifier = Modifier.height(LocalTheme.current.space.xsmall))

                    TextFieldGeneric(
                        value = lastName,
                        errorValidator = lastNameError,
                        onValueChange = { onChangeLastname(it) },
                        onFocusChange = { onBlurLastname(it)},
                        text = stringResource(R.string.user_last_name_signup),
                        iconId = R.drawable.ic_first_name,
                        iconDescription = stringResource(R.string.user_ic_desc_last_name_signup)
                    )
                    Spacer(modifier = Modifier.height(LocalTheme.current.space.xsmall))

                    TextFieldGeneric(
                        value = email,
                        errorValidator = emailError,
                        onValueChange = { onChangeEmail(it) },
                        onFocusChange = { onBlurEmail(it) },
                        text = stringResource(R.string.user_email),
                        iconId = R.drawable.ic_login_email,
                        iconDescription = stringResource(R.string.user_ic_desc_email_signup)
                    )
                    Spacer(modifier = Modifier.height(LocalTheme.current.space.xsmall))

                    TextFieldPassword(
                        value = password,
                        errorValidator = passwordError,
                        onValueChange = { onChangePassword(it) },
                        onFocusChange = { onBlurPassword(it)},
                        text = stringResource(R.string.user_password),
                        iconId = R.drawable.ic_login_password,
                        iconDescription = stringResource(R.string.user_ic_desc_login_pass)
                    )
                    Spacer(modifier = Modifier.height(LocalTheme.current.space.xsmall))

                    TextFieldPassword(
                        value = confirmPassword,
                        errorValidator = confirmPasswordError,
                        isEnable = isConfirmPasswordEnable,
                        onValueChange = { onChangeConfirmPassword(it) },
                        onFocusChange = { /* no es necesario ya se valida qu sea igual al password */},
                        text = stringResource(R.string.user_confirm_password_signup),
                        iconId = R.drawable.ic_login_password,
                        iconDescription = stringResource(R.string.user_ic_desc_login_pass)
                    )

                    Spacer(modifier = Modifier.height(LocalTheme.current.space.xsmall))

                    DropDownMenu(
                        value = gender,
                        onValueChange = { onChangeGender(it) },
                        text = stringResource(R.string.user_gender_signup),
                        conId = R.drawable.ic_gender,
                        iconDescription = stringResource(R.string.user_ic_desc_gender_signup),
                        options = listOf(
                            stringResource(R.string.user_male_signup),
                            stringResource(R.string.user_female_signup)
                        ),
                    )
                    Spacer(modifier = Modifier.height(LocalTheme.current.space.xsmall))

                    DatePicker(
                        value = birthDate,
                        onValueChange = { onChangeBirthDate(it) }, //method to update state attribute
                        text = stringResource(R.string.user_birth_date_signup), //label custom
                        conId = R.drawable.ic_date,
                        iconDescription = stringResource(R.string.user_ic_desc_gender_signup),
                    )
                    Spacer(modifier = Modifier.height(LocalTheme.current.space.xsmall))

                    TextFieldGeneric(
                        value = image,
                        errorValidator = imageError,
                        onValueChange = { onChangeImage(it) },
                        onFocusChange = { onBlurImage(it)},
                        text = stringResource(R.string.user_url_image_signup),
                        iconId = R.drawable.ic_image,
                        iconDescription = stringResource(R.string.user_ic_desc_url_image_signup)
                    )
                    Spacer(modifier = Modifier.height(LocalTheme.current.space.xsmall))

                    TextFieldGeneric(
                        value = phone,
                        errorValidator = phoneError,
                        onValueChange = { onChangePhone(it) },
                        onFocusChange = { onBlurPhone(it)},
                        text = stringResource(R.string.user_phone_signup),
                        iconId = R.drawable.ic_phone,
                        iconDescription = stringResource(R.string.user_ic_desc_phone_signup)
                    )
                    Spacer(modifier = Modifier.height(LocalTheme.current.space.large))

                    GradientButton(
                        text = stringResource(R.string.user_signup),
                        modifier = Modifier
                            .align(Alignment.End),
                        onClick = { onSignUp() },
                        isEnable = isSignUpButtonEnable,
                        icon = R.drawable.ic_login_button_arrow
                    )

                    Spacer(modifier = Modifier.height(LocalTheme.current.space.large))

                    ClickableText(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Gray,
                                    fontSize = LocalTheme.current.fontSize.xsmall,
                                    fontWeight = FontWeight.W600
                                )
                            ) {
                                append("Already have an account? ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xFFFF9700),
                                    fontSize = LocalTheme.current.fontSize.xsmall,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Sign in")
                            }
                        },
                        modifier = Modifier.padding(bottom = LocalTheme.current.padding.xlarge),
                        onClick = { onSignIn() }

                    )

                }
            }
        }
        if (signUpLoading) {
            UsersLoadingProgressIndicator()
        }
    }

}

@Composable
private fun UsersLoadingProgressIndicator() {
    Box(modifier = Modifier.fillMaxWidth()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}