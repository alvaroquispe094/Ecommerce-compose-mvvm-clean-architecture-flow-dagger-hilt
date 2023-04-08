package com.groupal.ecommerce.di

import android.content.Context
import com.groupal.configuration.ecommerce.data.IConfigurationRepository
import com.groupal.configuration.ecommerce.data.local.repository.TokenManager
import com.groupal.configuration.ecommerce.data.network.ConfigurationEndpoint
import com.groupal.configuration.ecommerce.data.network.repository.ConfigurationRepository
import com.groupal.product.ecommerce.data.IProductRepository
import com.groupal.product.ecommerce.data.network.ProductsEndpoint
import com.groupal.product.ecommerce.repository.ProductRepository
import com.groupal.user.ecommerce.data.IAuthRepository
import com.groupal.user.ecommerce.data.IUserRepository
import com.groupal.user.ecommerce.data.network.AuthEndpoint
import com.groupal.user.ecommerce.data.network.UsersEndpoint
import com.groupal.user.ecommerce.data.repository.AuthRepository
import com.groupal.user.ecommerce.data.repository.UserRepository
import com.groupal.user.ecommerce.data.utils.AuthAuthenticator
import com.groupal.user.ecommerce.data.utils.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val URL_BASE_PRODUCTION = "http://ecommerceapp-env-1.eba-xg323xbv.us-east-1.elasticbeanstalk.com"
    private const val URL_BASE_DEVELOP = "http://10.0.2.2:3000"
    private const val URL_BASE = URL_BASE_PRODUCTION // Por ahora apunta al backend deploy

    private const val PROVIDE_RETROFIT_AUTH_API = "provideRetrofitAuthAPI"
    private const val PROVIDE_RETROFIT_USER_API = "provideRetrofitUserAPI"
    private const val PROVIDE_RETROFIT_PRODUCT_API = "provideRetrofitProductAPI"
    private const val PROVIDE_RETROFIT_CONFIGURATION_API = "provideRetrofitConfigurationAPI"

    @Singleton
    @Provides
    @Named(PROVIDE_RETROFIT_AUTH_API)
    fun provideRetrofitAuthAPI(okHttpClient: OkHttpClient,): Retrofit {
        return buildRetrofit(okHttpClient,URL_BASE) //TODO: Parametrized URL
    }

    @Singleton
    @Provides
    @Named(PROVIDE_RETROFIT_USER_API)
    fun provideRetrofitUserAPI(okHttpClient: OkHttpClient,): Retrofit {
        return buildRetrofit(okHttpClient,URL_BASE) //TODO: Parametrized URL
    }

    @Singleton
    @Provides
    @Named(PROVIDE_RETROFIT_CONFIGURATION_API)
    fun provideRetrofitConfigurationAPI(okHttpClient: OkHttpClient,): Retrofit {
        return buildRetrofit(okHttpClient,URL_BASE) //TODO: Parametrized URL
    }

    @Singleton
    @Provides
    @Named(PROVIDE_RETROFIT_PRODUCT_API)
    fun provideRetrofitProductPI(okHttpClient: OkHttpClient,): Retrofit {
        return buildRetrofit(okHttpClient,URL_BASE) //TODO: Parametrized URL
    }

    @Singleton
    @Provides
    fun provideUsersEndpoint(@Named(PROVIDE_RETROFIT_USER_API) retrofit: Retrofit): UsersEndpoint {
        return retrofit.create(UsersEndpoint::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthEndpoint(@Named(PROVIDE_RETROFIT_AUTH_API) retrofit: Retrofit): AuthEndpoint {
        return retrofit.create(AuthEndpoint::class.java)
    }

    @Singleton
    @Provides
    fun provideConfigurationEndpoint(@Named(PROVIDE_RETROFIT_CONFIGURATION_API) retrofit: Retrofit): ConfigurationEndpoint {
        return retrofit.create(ConfigurationEndpoint::class.java)
    }

    @Singleton
    @Provides
    fun provideProductsEndpoint(@Named(PROVIDE_RETROFIT_PRODUCT_API) retrofit: Retrofit): ProductsEndpoint {
        return retrofit.create(ProductsEndpoint::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(authEndpoint: AuthEndpoint): IAuthRepository {
        return AuthRepository(authEndpoint)
    }

    @Singleton
    @Provides
    fun provideUserRepository(userEndpoint: UsersEndpoint): IUserRepository {
        return UserRepository(userEndpoint)
    }

    @Singleton
    @Provides
    fun provideProductRepository(productEndpoint: ProductsEndpoint): IProductRepository {
        return ProductRepository(productEndpoint)
    }

    @Singleton
    @Provides
    fun provideConfigurationNetworkRepository(configurationEndpoint: ConfigurationEndpoint): IConfigurationRepository {
        return ConfigurationRepository(configurationEndpoint)
    }

    // Interceptor && Authentication
    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager = TokenManager(context)

    @Singleton
    @Provides
    fun provideUrl(): String = URL_BASE

    @Singleton
    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManager): AuthInterceptor =
        AuthInterceptor(tokenManager)

    @Singleton
    @Provides
    fun provideAuthAuthenticator(tokenManager: TokenManager, url: String): AuthAuthenticator =
        AuthAuthenticator(tokenManager, url)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .authenticator(authAuthenticator)
            .build()
    }

    // Build Retrofit
    @Provides
    fun buildRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {

        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

}