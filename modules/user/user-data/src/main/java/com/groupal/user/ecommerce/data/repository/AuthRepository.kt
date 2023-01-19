package com.groupal.user.ecommerce.data.repository

import com.groupal.shared.ecommerce.domain.GenericException
import com.groupal.user.ecommerce.data.IAuthRepository
import com.groupal.shared.ecommerce.data.network.model.*
import com.groupal.shared.ecommerce.data.network.util.execute
import com.groupal.shared.ecommerce.data.network.util.handlerResponse
import com.groupal.user.ecommerce.data.network.AuthEndpoint
import com.groupal.user.ecommerce.data.network.model.LoginRequestRest
import com.groupal.user.ecommerce.data.network.model.LoginResponseRest
import com.groupal.user.ecommerce.data.network.model.SignUpRequestRest
import com.groupal.user.ecommerce.data.network.model.SignUpResponseRest
import com.groupal.user.ecommerce.domain.LoginResponse
import com.groupal.user.ecommerce.domain.SignUpRequest
import javax.inject.Inject


class AuthRepository @Inject constructor(private val authEndpoint: AuthEndpoint) : IAuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): LoginResponse {
        val loginResponse: Result<LoginResponseRest?> =
            execute { authEndpoint.login(LoginRequestRest(email, password)) }

        return handlerResponse(loginResponse) { successResponse ->
            successResponse.value?.toDomain() ?: throw GenericException(GenericError.LOGIN_ERROR.description)
        }
    }

    override suspend fun signUp(signUpRequest: SignUpRequest) {
        val signUpResponse: Result<SignUpResponseRest?> =
            execute { authEndpoint.signUp(SignUpRequestRest.fromDomain(signUpRequest))}

        return handlerResponse(signUpResponse) { }
    }
}
