package com.groupal.ecommerce.di

import com.groupal.ecommerce.common.Constants
import com.groupal.ecommerce.data.remote.MockApi
import com.groupal.ecommerce.data.repository.CategoryRepository
import com.groupal.ecommerce.data.repository.ProductRepository
import com.groupal.ecommerce.domain.repository.ICategory
import com.groupal.ecommerce.domain.repository.IProduct
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): MockApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MockApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: MockApi): IProduct {
        return ProductRepository(api)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(api: MockApi): ICategory {
        return CategoryRepository(api)
    }

}