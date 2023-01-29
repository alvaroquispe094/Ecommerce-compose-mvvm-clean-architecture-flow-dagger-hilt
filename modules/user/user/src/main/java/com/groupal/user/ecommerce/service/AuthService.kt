package com.groupal.user.ecommerce.service

import com.groupal.configuration.ecommerce.data.local.repository.TokenManager
import com.groupal.shared.ecommerce.di.ApplicationCoroutineScope
import com.groupal.shared.ecommerce.di.IODispatcher
import com.groupal.shared.ecommerce.domain.GenericException
import com.groupal.shared.ecommerce.service.LogService
import com.groupal.user.ecommerce.data.IAuthRepository
import com.groupal.user.ecommerce.domain.LoginResponse
import com.groupal.user.ecommerce.domain.SignUpRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(
    @ApplicationCoroutineScope private val configurationScope: CoroutineScope,
    @IODispatcher ioDispatcher: CoroutineDispatcher,
    private val logService: LogService,
    private val authRepository: IAuthRepository,
    private val tokenManager: TokenManager,
    ) {

    init {
         configurationScope.launch(ioDispatcher) {
             getConfigurations()
         }
    }

    //Login
    private val _loginSession = MutableStateFlow<LoginResponse?>(null)
    val loginSession: StateFlow<LoginResponse?> get() = _loginSession.asStateFlow()

    private val _loginError = MutableStateFlow<GenericException?>(null)
    val loginError: StateFlow<GenericException?> get() = _loginError.asStateFlow()

    //Token
    val tokenSession: StateFlow<String?> = tokenManager.tokenSession

    //Sign up
    private val _isSignUpOk = MutableStateFlow(false)
    val isSignUpOk: StateFlow<Boolean> get() = _isSignUpOk.asStateFlow()

    private val _signUpError = MutableStateFlow<GenericException?>(null)
    val signUpError: StateFlow<GenericException?> get() = _signUpError.asStateFlow()

    suspend fun login(username: String, password: String) {
        logService.info("LOGIN", "$username, $password")
        try {
            val loginResponse = authRepository.login(username, password)
            tokenManager.saveToken(loginResponse.accessToken)
            tokenManager.emitToken(loginResponse.accessToken)
            _loginSession.emit(loginResponse)
        } catch (e: GenericException) {
            logService.error("LOGIN", e.message.toString(), e.cause)
            _loginError.emit(e)
        }
    }

    suspend fun logout() {
        tokenManager.deleteToken()
        tokenManager.emitToken(null)
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

    suspend fun getConfigurations() {
        logService.info("CONFIGURATION", "GET FEATURE FLAGS")
        try {
            val token = tokenManager.getToken().first()
            logService.info("Token: ", token.toString())
            tokenManager.emitToken(token)

        } catch (e: GenericException) {
            logService.error(" FAIL CONFIGURATION TOKEN", e.message.toString(), e.cause)
        }
    }

}