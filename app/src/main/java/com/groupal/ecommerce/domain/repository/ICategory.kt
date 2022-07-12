package com.groupal.ecommerce.domain.repository

import com.groupal.ecommerce.data.remote.dto.CategoryDto

interface ICategory {
    suspend fun getCategories(): List<CategoryDto>
}