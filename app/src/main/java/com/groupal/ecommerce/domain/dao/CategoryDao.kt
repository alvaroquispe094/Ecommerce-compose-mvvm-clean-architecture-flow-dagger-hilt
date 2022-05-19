package com.groupal.ecommerce.domain.dao

import com.groupal.ecommerce.data.remote.dto.CategoryDto

interface CategoryDao {
    suspend fun getCategories(): List<CategoryDto>
}