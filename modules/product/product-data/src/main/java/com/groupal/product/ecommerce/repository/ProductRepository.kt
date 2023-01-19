package com.groupal.product.ecommerce.repository

import com.groupal.product.ecommerce.data.IProductRepository
import com.groupal.product.ecommerce.data.network.ProductsEndpoint
import com.groupal.product.ecommerce.domain.Product
import com.groupal.shared.ecommerce.data.network.model.GenericError
import com.groupal.shared.ecommerce.data.network.util.execute
import com.groupal.shared.ecommerce.data.network.util.handlerResponse
import com.groupal.shared.ecommerce.domain.GenericException
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productEndpoint: ProductsEndpoint,
) : IProductRepository {

    override suspend fun getProducts(): List<Product> = handlerResponse(execute { productEndpoint.getProducts() }) { successResponse ->
        successResponse.value
            ?.map { it.toDomain() }
            ?: throw GenericException(GenericError.LOGIN_ERROR.description)
    }
}
