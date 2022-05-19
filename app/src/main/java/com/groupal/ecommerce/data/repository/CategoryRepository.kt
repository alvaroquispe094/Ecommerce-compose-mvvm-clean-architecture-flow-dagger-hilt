package com.groupal.ecommerce.data.repository

import com.groupal.ecommerce.data.remote.MockApi
import com.groupal.ecommerce.data.remote.dto.CategoryDto
import com.groupal.ecommerce.data.remote.dto.ProductDto
import com.groupal.ecommerce.domain.dao.CategoryDao
import com.groupal.ecommerce.domain.dao.ProductDao
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val api: MockApi
) : CategoryDao {

    override suspend fun getCategories(): List<CategoryDto> {
        return api.getAllCategories()
    }
}