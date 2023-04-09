package com.groupal.user.ecommerce.data.service

import com.groupal.user.ecommerce.data.utils.AuthInterceptor2
import com.groupal.user.ecommerce.service.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataModuleConfigurationService @Inject constructor(
    private val authService: AuthService
) {
    fun configure(scope: CoroutineScope) {
        AuthInterceptor2.configure(
            scope,
            authService.session.map {
                it?.sessionToken
            }
        )
    }
}
