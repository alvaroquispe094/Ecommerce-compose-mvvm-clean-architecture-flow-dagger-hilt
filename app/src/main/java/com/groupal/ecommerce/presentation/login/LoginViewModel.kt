package com.groupal.ecommerce.presentation.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupal.ecommerce.common.Resource
import com.groupal.ecommerce.domain.model.Product
import com.groupal.ecommerce.domain.use_case.categories.GetCategoriesUseCase
import com.groupal.ecommerce.domain.use_case.products.GetProductUseCase
import com.groupal.ecommerce.domain.use_case.products.GetProductsUseCase
import com.groupal.ecommerce.presentation.home.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state

    fun init(){

    }

    fun login(user: String, password: String) {
        Log.i(TAG, "IS Login")
        Log.i(TAG, user)
        Log.i(TAG, password)

        _state.update {
            it.copy(
                isLoginOpen = false,
                isLoading = false
            )
        }

//        navigateToHome()
    }

    /**
     * Selects the given article to view more information about it.
     */
    fun selectProduct(product: Product) {

//        getProductUseCase(product.id).onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _state.update {
//                        it.copy(
//                            product = result.data,
//                            isProductOpen = true,
////                            isHomeOpen = false,
//                            isLoading = false
//                        )
//                    }
//                }
//                is Resource.Error -> {
//                    _state.value = HomeScreenState(
//                        error = result.message ?: "An unexpected error occured"
//                    )
//                }
//                is Resource.Loading -> {
//                    _state.update {
//                        it.copy(
//                            isLoading = true
//                        )
//                    }
//                }
//            }
//        }.launchIn(viewModelScope)

        // Treat selecting a detail as simply interacting with it
//        _state.update {
//            it.copy(
//                isProductOpen = true,
////                isHomeOpen = false,
//                product = product
//            )
//        }
//        getProducts()
    }

    /**
     * Notify that the user interacted with the feed
     */
//    fun interactedWithFeed() {
//        _state.update {
//            it.copy(
//                isProductOpen = false,
//            )
//        }
//    }
}