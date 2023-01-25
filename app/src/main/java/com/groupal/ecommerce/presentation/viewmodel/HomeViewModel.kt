package com.groupal.ecommerce.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupal.configuration.ecommerce.service.ConfigurationService
import com.groupal.product.ecommerce.domain.Product
import com.groupal.product.ecommerce.service.ProductService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productService: ProductService
) : ViewModel() {

    val products: StateFlow<List<Product>> = productService.products.map { it ?: emptyList() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    val error: StateFlow<Throwable?> = productService.error

    private val _homeLoading = MutableStateFlow(false)
    val homeLoading: StateFlow<Boolean> get() = _homeLoading.asStateFlow()

    init{
        refresh()
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            productService.refreshAllUsers()
        }
    }
}