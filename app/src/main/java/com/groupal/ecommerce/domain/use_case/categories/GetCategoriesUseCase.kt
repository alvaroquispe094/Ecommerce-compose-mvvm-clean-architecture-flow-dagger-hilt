package com.groupal.ecommerce.domain.use_case.categories

import com.groupal.ecommerce.common.Resource
import com.groupal.ecommerce.data.remote.dto.toCategory
import com.groupal.ecommerce.data.repository.CategoryRepository
import com.groupal.ecommerce.domain.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    operator fun invoke(): Flow<Resource<List<Category>>> = flow {
        try {
            emit(Resource.Loading<List<Category>>())
            val categories = repository.getCategories().map { it.toCategory() }
            emit(Resource.Success(categories))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}