package com.groupal.ecommerce.data.repository

import com.groupal.ecommerce.data.remote.MockApi
import com.groupal.ecommerce.data.remote.dto.CategoryDto
import com.groupal.ecommerce.domain.repository.ICategory
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val api: MockApi
) : ICategory {

    override suspend fun getCategories(): List<CategoryDto> {
        return api.getAllCategories()
    }
}