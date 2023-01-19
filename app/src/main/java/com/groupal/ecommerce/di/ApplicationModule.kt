package com.groupal.ecommerce.di

import com.groupal.shared.ecommerce.di.ApplicationCoroutineScope
import com.groupal.shared.ecommerce.di.IODispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    private var _coroutineScope: CoroutineScope? = null
    private val coroutineScope: CoroutineScope get() = _coroutineScope ?: throw IllegalStateException("I must set a coroutine scope from the activity")

    fun setApplicationScope(coroutineScope: CoroutineScope) {
        _coroutineScope = coroutineScope
    }

    @ApplicationCoroutineScope
    @Singleton
    @Provides
    fun provideApplicationScope(): CoroutineScope {
        return coroutineScope
    }

    @IODispatcher
    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}