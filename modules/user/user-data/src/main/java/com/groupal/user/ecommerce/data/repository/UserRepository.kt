package com.groupal.user.ecommerce.data.repository

import com.groupal.shared.ecommerce.data.network.util.execute
import com.groupal.shared.ecommerce.data.network.util.handlerResponse
import com.groupal.user.ecommerce.data.IUserRepository
import com.groupal.user.ecommerce.data.network.UsersEndpoint
import javax.inject.Inject

class UserRepository @Inject constructor(private val userEndpoint: UsersEndpoint) :
    IUserRepository {
    override suspend fun existUserByEmail(email: String): Boolean =
        handlerResponse(
            execute { userEndpoint.existUserByEmail(email) }
        ) { it.value ?: false }
}
