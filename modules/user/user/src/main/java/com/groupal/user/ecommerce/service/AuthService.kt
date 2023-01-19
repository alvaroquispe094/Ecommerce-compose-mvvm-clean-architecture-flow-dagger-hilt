package com.groupal.user.ecommerce.service

import com.groupal.shared.ecommerce.domain.GenericException
import com.groupal.shared.ecommerce.service.LogService
import com.groupal.user.ecommerce.data.IAuthRepository
import com.groupal.user.ecommerce.domain.LoginResponse
import com.groupal.user.ecommerce.domain.SignUpRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(
    private val logService: LogService,
    private val authRepository: IAuthRepository
    ) {

    //Login
    private val _loginSession = MutableStateFlow<LoginResponse?>(null)
    val loginSession: StateFlow<LoginResponse?> get() = _loginSession.asStateFlow()

    private val _loginError = MutableStateFlow<GenericException?>(null)
    val loginError: StateFlow<GenericException?> get() = _loginError.asStateFlow()

    //Sign up
    private val _isSignUpOk = MutableStateFlow(false)
    val isSignUpOk: StateFlow<Boolean> get() = _isSignUpOk.asStateFlow()

    private val _signUpError = MutableStateFlow<GenericException?>(null)
    val signUpError: StateFlow<GenericException?> get() = _signUpError.asStateFlow()

    suspend fun login(username: String, password: String) {
        logService.info("LOGIN", "$username, $password")
        try {
            val loginResponse = authRepository.login(username, password)
            _loginSession.emit(loginResponse)
        } catch (e: GenericException) {
            logService.error("LOGIN", e.message.toString(), e.cause)
            _loginError.emit(e)
        }
    }

    suspend fun logout() {
        _loginSession.emit(null)
    }

    suspend fun signUp(signUpRequest: SignUpRequest) {
        logService.info("SERVICE REQUEST SIGN UP", "$signUpRequest")
        try {
            authRepository.signUp(signUpRequest)
            _isSignUpOk.emit(true)
        } catch (e: GenericException) {
            logService.error("ERROR SERVICE SIGN UP", e.message.toString(), e.cause)
            _signUpError.emit(e)
            _isSignUpOk.emit(false)
        }
    }

    suspend fun cleanLoginError() {
        _loginError.emit(null)
    }

    suspend fun cleanSignUpError() {
        _signUpError.emit(null)
    }

}