package com.groupal.user.ecommerce.service

import com.groupal.configuration.ecommerce.data.IConfigurationLocalRepository
import com.groupal.configuration.ecommerce.data.local.repository.TokenManager
import com.groupal.shared.ecommerce.di.ApplicationCoroutineScope
import com.groupal.shared.ecommerce.di.IODispatcher
import com.groupal.shared.ecommerce.domain.GenericException
import com.groupal.shared.ecommerce.service.LogService
import com.groupal.shared.ecommerce.service.MessageService
import com.groupal.shared.ecommerce.utils.MessageType
import com.groupal.user.ecommerce.data.IAuthLocalRepository
import com.groupal.user.ecommerce.data.IAuthRepository
import com.groupal.user.ecommerce.domain.Login
import com.groupal.user.ecommerce.domain.LoginResponse
import com.groupal.user.ecommerce.domain.SignUpRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(
    /*@ApplicationCoroutineScope private val configurationScope: CoroutineScope,
    @IODispatcher ioDispatcher: CoroutineDispatcher,*/
    private val logService: LogService,
    private val authRepository: IAuthRepository,
    //private val tokenManager: TokenManager,
    private val authLocalRepository: IAuthLocalRepository,
    private val localRepository: IConfigurationLocalRepository,
    private val messageService: MessageService,
    ) {

    private var localDataCollectionJob: Job? = null

    //Login
    private val _loginSession = MutableStateFlow<LoginResponse?>(null)
    val loginSession: StateFlow<LoginResponse?> get() = _loginSession.asStateFlow()

    private val _loginError = MutableStateFlow<GenericException?>(null)
    val loginError: StateFlow<GenericException?> get() = _loginError.asStateFlow()

    //Token
    //val tokenSession: StateFlow<String?> = tokenManager.tokenSession

    //Sign up
    private val _isSignUpOk = MutableStateFlow(false)
    val isSignUpOk: StateFlow<Boolean> get() = _isSignUpOk.asStateFlow()

    private val _signUpError = MutableStateFlow<GenericException?>(null)
    val signUpError: StateFlow<GenericException?> get() = _signUpError.asStateFlow()

    // Login
    private val _session = MutableStateFlow<Login?>(null)
    val session: StateFlow<Login?> get() = _session.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    fun initialize(scope: CoroutineScope) {
        localDataCollectionJob = scope.launch {
            authLocalRepository.data.collect {
                it?.also {
                    _isLoggedIn.emit(true)
                    _session.emit(Login(sessionToken = it))
                    localDataCollectionJob?.cancel()
                }
            }
        }
    }


    suspend fun login(username: String, password: String) {
        logService.info("LOGIN", "$username, $password")
        localDataCollectionJob?.cancel()
        try {
            val loginResponse = authRepository.login(username, password)
            /*tokenManager.saveToken(loginResponse.accessToken)
            tokenManager.emitToken(loginResponse.accessToken)*/
            _isLoggedIn.emit(true)
            _session.emit(Login(loginResponse.accessToken))
            authLocalRepository.saveSession(Login(loginResponse.accessToken))
            messageService.showMessage(MessageType.SuccessLogin)
        } catch (e: GenericException) {
            logService.error("LOGIN", e.message.toString(), e.cause)
            _loginError.emit(e)
            messageService.showMessage(MessageType.ErrorLogin)
        }
    }

    suspend fun logout() {
        localDataCollectionJob?.cancel()
        _isLoggedIn.emit(false)
        _session.emit(null)
        authLocalRepository.clear()
        localRepository.clear()
        /*tokenManager.deleteToken()
        tokenManager.emitToken(null)*/
        //_loginSession.emit(null)
        //messageService.showMessage(MessageType.SuccessLogin)
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

    /*suspend fun getConfigurations() {
        logService.info("CONFIGURATION", "GET FEATURE FLAGS")
        try {
        *//*    val token = tokenManager.getToken().first()
            logService.info("Token: ", token.toString())
            tokenManager.emitToken(token)*//*

        } catch (e: GenericException) {
            logService.error(" FAIL CONFIGURATION TOKEN", e.message.toString(), e.cause)
        }
    }*/

    /*fun initialize(scope: CoroutineScope) {
        logService.info("CONFIGURATION", "GET FEATURE FLAGS")
        try {
            val token = tokenManager.getToken().first()
            logService.info("Token: ", token.toString())
            tokenManager.emitToken(token)

        } catch (e: GenericException) {
            logService.error(" FAIL CONFIGURATION TOKEN", e.message.toString(), e.cause)
        }
    }*/

}