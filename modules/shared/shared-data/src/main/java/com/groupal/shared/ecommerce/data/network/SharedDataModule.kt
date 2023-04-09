package com.groupal.shared.ecommerce.data.network

import com.groupal.shared.ecommerce.data.network.interceptor.DatafastApiInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedDataModule {
    @Singleton
    @Provides
    fun provideDatafastApiInterceptor(): DatafastApiInterceptor = DatafastApiInterceptor()
}
