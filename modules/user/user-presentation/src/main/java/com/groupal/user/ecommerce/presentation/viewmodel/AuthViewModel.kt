package com.groupal.user.ecommerce.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupal.user.ecommerce.domain.LoginResponse
import com.groupal.user.ecommerce.domain.SignUpRequest
import com.groupal.user.ecommerce.service.AuthService
import com.groupal.user.ecommerce.service.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authService: AuthService,
    private val userService: UserService,
//    private val configurationService: ConfigurationService
) : ViewModel() {

    val loginSession: StateFlow<LoginResponse?> = authService.loginSession
    val sessionToken: StateFlow<String?> = authService.tokenSession

    val loginError: StateFlow<String?> =
        authService.loginError.map { exception -> exception?.message }
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val _loginLoading = MutableStateFlow(false)
    val loginLoading: StateFlow<Boolean> get() = _loginLoading.asStateFlow()

    //Sign up
    val isSignUpOk: StateFlow<Boolean> = authService.isSignUpOk
    val signUpError: StateFlow<String?> =
        authService.signUpError.map { exception -> exception?.message }
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val _signUpLoading = MutableStateFlow(false)
    val signUpLoading: StateFlow<Boolean> get() = _signUpLoading.asStateFlow()

    //User Exist
    val userExist: StateFlow<Boolean> = userService.existUser
    val userExistError: StateFlow<String?> =
        userService.existUserError.map { exception -> exception?.message }
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    // These flows represents the term which the user is searching for
    private val _emailSearch = MutableStateFlow("")
    val emailSearch: StateFlow<String> = _emailSearch.asStateFlow()

    /*val isSignUpEnabled: StateFlow<Boolean> = configurationService.configuration.map {
        it.isSignUpEnable
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)*/

    init {
        viewModelScope.launch {
            emailSearch.debounce(3000).collect { mail ->
                cleanUserExist()
                existUser(mail)
            }
        }
    }

    fun setSearchEmail(it: String) {
        _emailSearch.value = it
    }

    suspend fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginLoading.emit(true)
            authService.login(username, password)
            _loginLoading.emit(false)
        }
    }

    suspend fun logout() {
        viewModelScope.launch {
            authService.logout()
        }
    }

    suspend fun cleanLoginError() {
        authService.cleanLoginError()
    }

    suspend fun signUp(signUpRequest: SignUpRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            _signUpLoading.emit(true)
            authService.signUp(signUpRequest)
            delay(3000)
            _signUpLoading.emit(false)
        }
    }

    private suspend fun existUser(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userService.existUserByEmail(email)
        }
    }

    suspend fun cleanSignUpError() {
        authService.cleanSignUpError()
    }

    private suspend fun cleanUserExist() {
        userService.cleanUserExist()
    }
}