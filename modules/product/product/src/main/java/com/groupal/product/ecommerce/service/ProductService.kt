package com.groupal.product.ecommerce.service

import com.groupal.product.ecommerce.data.IProductRepository
import com.groupal.product.ecommerce.domain.Product
import com.groupal.shared.ecommerce.service.LogService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductService @Inject constructor(
    private val logService: LogService,
    private val productRepository: IProductRepository
) {
    private var dispatcher: CoroutineDispatcher = Dispatchers.IO

    private val errorFlow = MutableStateFlow<Throwable?>(null)
    private val productsFlow = MutableStateFlow<List<Product>?>(null)

    val products: StateFlow<List<Product>?> get() = productsFlow.asStateFlow()
    val error: StateFlow<Throwable?> get() = errorFlow.asStateFlow()

    private val _homeLoading = MutableStateFlow(false)
    val homeLoading: StateFlow<Boolean> = _homeLoading.asStateFlow()

    fun initialize() {
        MainScope().launch(dispatcher) {
            getProducts()
        }
    }

    fun initialize(dispatcher: CoroutineDispatcher) {
        this.dispatcher = dispatcher
        initialize()
    }


    fun refreshAllUsers() {
        MainScope().launch(dispatcher) {
            _homeLoading.emit(true)
            getProducts()
            _homeLoading.emit(false)
        }
    }


    private suspend fun getProducts() {
        val users: List<Product>

        try {
            users = productRepository.getProducts()
        } catch (e: Throwable) {
            errorFlow.emit(e)
            return
        }

        productsFlow.emit(users)
    }
}