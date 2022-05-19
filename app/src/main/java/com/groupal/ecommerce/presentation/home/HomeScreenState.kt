package com.groupal.ecommerce.presentation.home

import com.groupal.ecommerce.domain.model.Category
import com.groupal.ecommerce.domain.model.Product


data class HomeScreenState(
    val isHomeOpen: Boolean = true,
    val isLoading: Boolean = true,
    val products: List<Product> = emptyList(),
    val categories: List<Category> = emptyList(),
    val product: Product? = null,
    val isProductOpen: Boolean = false,
    val error: String = ""
)