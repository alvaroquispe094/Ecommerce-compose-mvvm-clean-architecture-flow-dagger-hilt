package com.groupal.user.ecommerce.service

import com.groupal.shared.ecommerce.domain.GenericException
import com.groupal.shared.ecommerce.service.LogService
import com.groupal.user.ecommerce.data.IUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(
    private val logService: LogService,
    private val userRepository: IUserRepository
) {
    private val _existUser = MutableStateFlow(false)
    val existUser: StateFlow<Boolean> get() = _existUser.asStateFlow()

    private val _existUserError = MutableStateFlow<GenericException?>(null)
    val existUserError: StateFlow<GenericException?> get() = _existUserError.asStateFlow()

    suspend fun existUserByEmail(email: String) {
        logService.info("SERVICE REQUEST USER EXIST BY EMAIL?", email)
        try {
            val existUserResponse = userRepository.existUserByEmail(email)
            _existUser.emit(existUserResponse)
        } catch (e: GenericException) {
            logService.error("ERROR SERVICE REQUEST USER EXIST BY EMAIL", e.message.toString(), e.cause)
            _existUserError.emit(e)
            _existUser.emit(false)
        }
    }

    suspend fun cleanUserExist() {
        _existUser.emit(false)
    }
}