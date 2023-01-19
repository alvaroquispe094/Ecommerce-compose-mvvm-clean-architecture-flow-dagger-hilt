package com.groupal.ecommerce.di

import android.content.Context
import com.groupal.configuration.ecommerce.data.IConfigurationLocalRepository
import com.groupal.configuration.ecommerce.data.IConfigurationRepository
import com.groupal.configuration.ecommerce.data.local.repository.ConfigurationLocalRepository
import com.groupal.configuration.ecommerce.data.network.ConfigurationEndpoint
import com.groupal.configuration.ecommerce.data.network.repository.ConfigurationRepository
import com.groupal.product.ecommerce.data.IProductRepository
import com.groupal.product.ecommerce.data.network.ProductsEndpoint
import com.groupal.product.ecommerce.repository.ProductRepository
import com.groupal.shared.ecommerce.data.network.model.OAuthInterceptor
import com.groupal.user.ecommerce.data.IAuthRepository
import com.groupal.user.ecommerce.data.IUserRepository
import com.groupal.user.ecommerce.data.network.AuthEndpoint
import com.groupal.user.ecommerce.data.network.UsersEndpoint
import com.groupal.user.ecommerce.data.repository.AuthRepository
import com.groupal.user.ecommerce.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val PROVIDE_RETROFIT_AUTH_API = "provideRetrofitAuthAPI"
    private const val PROVIDE_RETROFIT_USER_API = "provideRetrofitUserAPI"
    private const val PROVIDE_RETROFIT_PRODUCT_API = "provideRetrofitProductAPI"
    private const val PROVIDE_RETROFIT_CONFIGURATION_API = "provideRetrofitConfigurationAPI"

    /* TODO: Hoy por hoy no tiene sentido tener 3 provides distintos ya que esta todo en un mismo microservicio, pero a futuro al ser dominios distintos deberian separarse.*/
    @Singleton
    @Provides
    @Named(PROVIDE_RETROFIT_AUTH_API)
    fun provideRetrofitAuthAPI(): Retrofit {
        return buildRetrofit("https://crazy-gun-production.up.railway.app") //TODO: Parametrized URL
    }

    @Singleton
    @Provides
    @Named(PROVIDE_RETROFIT_USER_API)
    fun provideRetrofitUserAPI(): Retrofit {
        return buildRetrofit("https://crazy-gun-production.up.railway.app") //TODO: Parametrized URL
    }

    @Singleton
    @Provides
    @Named(PROVIDE_RETROFIT_CONFIGURATION_API)
    fun provideRetrofitConfigurationAPI(): Retrofit {
        return buildRetrofit("https://crazy-gun-production.up.railway.app") //TODO: Parametrized URL
    }

    @Singleton
    @Provides
    @Named(PROVIDE_RETROFIT_PRODUCT_API)
    fun provideRetrofitProductPI(): Retrofit {
        return buildRetrofit("https://crazy-gun-production.up.railway.app") //TODO: Parametrized URL
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
        return ProductRepository(
            productEndpoint
        )
    }

    @Singleton
    @Provides
    fun provideConfigurationNetworkRepository(configurationEndpoint: ConfigurationEndpoint): IConfigurationRepository {
        return ConfigurationRepository(
            configurationEndpoint
        )
    }

    @Singleton
    @Provides
    fun provideConfigurationLocalRepository(@ApplicationContext context: Context): IConfigurationLocalRepository {
        return ConfigurationLocalRepository(
            context = context
        )
    }

    /*val client = OkHttpClient.Builder()
        .addInterceptor(OAuthInterceptor("Bearer", accessToken))
        .build()
    */
   /* var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqZ29uemFsZXpAZ21haWwuY29tIiwiaWF0IjoxNjczOTAzODc3LCJleHAiOjE2NzM5OTAyNzd9.5Seg0qtvCTLewPtf7aKR5FrERhKH3HCHFXfy3tMZHb06anxGUAmvN8KsD00xl4L-OpBnYzBZt2MwI7wH0Bwyxg")
            .build()
        chain.proceed(newRequest)
    }).build()*/

    val client =  OkHttpClient.Builder()
        .addInterceptor(OAuthInterceptor("Bearer", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqZ29uemFsZXpAZ21haWwuY29tIiwiaWF0IjoxNjc0MDU1NzA4LCJleHAiOjE2NzQxNDIxMDh9.EhvuIPRQM-6XMAaBZkp9wFi426zLpXuUCNpiqade4eGAHK8JENZcPAtIDCIg5YtenZp8QbR2FWj6UDOU1hPzDA"))
        .build()

    private fun buildRetrofit(url: String): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            //.client(okHttpClient)
            .build()

        //TODO: Add client or interceptor Okhttp
    }

}