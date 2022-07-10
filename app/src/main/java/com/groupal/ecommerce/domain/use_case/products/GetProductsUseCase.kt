package com.groupal.ecommerce.domain.use_case.products

import com.groupal.ecommerce.common.Resource
import com.groupal.ecommerce.data.remote.dto.toProduct
import com.groupal.ecommerce.data.repository.ProductRepository
import com.groupal.ecommerce.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductsUseCase  @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading<List<Product>>())
             val coins = repository.getProducts().map { it.toProduct() }
            emit(Resource.Success(coins))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Product>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Product>>("Couldn't reach server. Check your internet connection."))
        }
    }
}