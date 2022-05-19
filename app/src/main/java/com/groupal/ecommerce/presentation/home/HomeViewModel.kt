package com.groupal.ecommerce.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupal.ecommerce.common.Resource
import com.groupal.ecommerce.domain.model.Product
import com.groupal.ecommerce.domain.use_case.categories.GetCategoriesUseCase
import com.groupal.ecommerce.domain.use_case.products.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


/**
 * ViewModel that handles the business logic of the Home screen
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state

    fun getProducts() {
        getProductsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            products = result.data ?: emptyList(),
                            labels = true,
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> {
                    _state.value = HomeScreenState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getCategories() {
        getCategoriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            categories = result.data ?: emptyList(),
//                            isLoading = false,
                        )
                    }
                }
                is Resource.Error -> {
                    _state.value = HomeScreenState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    init {
        getCategories()
        getProducts()
    }

    /**
     * Selects the given article to view more information about it.
     */
    fun selectProduct(product: Product) {
        // Treat selecting a detail as simply interacting with it
        _state.update {
            it.copy(
                isProductOpen = true,
//                isHomeOpen = false,
                product = product
            )
        }
//        getProducts()
    }

    /**
     * Notify that the user interacted with the feed
     */
    fun interactedWithFeed() {
        _state.update {
            it.copy(
                isProductOpen = false,
            )
        }
//        getProducts()
    }
}