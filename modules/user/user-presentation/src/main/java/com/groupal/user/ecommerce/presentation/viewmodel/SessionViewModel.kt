package com.groupal.user.ecommerce.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.groupal.user.ecommerce.service.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    authService: AuthService
) : ViewModel() {
    val session = authService.session
}
