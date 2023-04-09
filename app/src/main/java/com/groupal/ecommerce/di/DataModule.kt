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
import com.groupal.shared.ecommerce.data.di.UnauthenticatedHttpClient
import com.groupal.shared.ecommerce.data.network.interceptor.DatafastApiInterceptor
import com.groupal.shared.ecommerce.data.network.interceptor.DatafastLoggingInterceptor
import com.groupal.shared.ecommerce.data.network.util.buildRetrofit
import com.groupal.user.ecommerce.data.IAuthRepository
import com.groupal.user.ecommerce.data.IUserRepository
import com.groupal.user.ecommerce.data.network.AuthEndpoint
import com.groupal.user.ecommerce.data.network.UsersEndpoint
import com.groupal.user.ecommerce.data.repository.AuthRepository
import com.groupal.user.ecommerce.data.repository.UserRepository
import com.groupal.user.ecommerce.data.utils.AuthInterceptor2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    private const val PROVIDE_RETROFIT_PRODUCT_API = "provideRetrofitProductAPI"
    private const val PROVIDE_RETROFIT_CONFIGURATION_API = "provideRetrofitConfigurationAPI"

    @Singleton
    @Provides
    @Named(PROVIDE_RETROFIT_CONFIGURATION_API)
    fun provideRetrofitConfigurationAPI(okHttpClient: OkHttpClient): Retrofit {
        return buildRetrofit(
            okHttpClient,
            "http://ecommerceapp-env-1.eba-xg323xbv.us-east-1.elasticbeanstalk.com/"
        ) // TODO: Parametrized URL
    }

    @Singleton
    @Provides
    @Named(PROVIDE_RETROFIT_PRODUCT_API)
    fun provideRetrofitProductPI(okHttpClient: OkHttpClient): Retrofit {
        return buildRetrofit(
            okHttpClient,
            "http://ecommerceapp-env-1.eba-xg323xbv.us-east-1.elasticbeanstalk.com/"
        ) // TODO: Parametrized URL
    }

    @Singleton
    @Provides
    fun provideConfigurationEndpoint(
        @Named(PROVIDE_RETROFIT_CONFIGURATION_API) retrofit: Retrofit
    ): ConfigurationEndpoint {
        return retrofit.create(ConfigurationEndpoint::class.java)
    }

    @Singleton
    @Provides
    fun provideProductsEndpoint(
        @Named(PROVIDE_RETROFIT_PRODUCT_API) retrofit: Retrofit
    ): ProductsEndpoint {
        return retrofit.create(ProductsEndpoint::class.java)
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
    fun provideConfigurationNetworkRepository(
        configurationEndpoint: ConfigurationEndpoint
    ): IConfigurationRepository {
        return ConfigurationRepository(
            configurationEndpoint
        )
    }

    @Singleton
    @Provides
    fun provideConfigurationLocalRepository(
        @ApplicationContext context: Context
    ): IConfigurationLocalRepository {
        return ConfigurationLocalRepository(
            context = context
        )
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        /*authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator,*/
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            //.addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            //.authenticator(authAuthenticator)
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

    @Singleton
    @Provides
    @UnauthenticatedHttpClient
    fun provideOwnApiOkHttpClient(
        datafastApiInterceptor: DatafastApiInterceptor
    ): OkHttpClient = ownApiHttpClientBuilder(datafastApiInterceptor).build()

   /* @Singleton
    @Provides
    @UnauthenticatedHttpClient
    fun provideOwnApiOkHttpClient(
        datafastApiInterceptor: AuthInterceptor2
    ): OkHttpClient = ownApiHttpClientBuilder(datafastApiInterceptor).build()
*/
    /*@Singleton
    @Provides
    fun provideConfigurationLocalRepository(
        @ApplicationContext context: Context
    ): IConfigurationLocalRepository {
        return ConfigurationLocalRepository(
            context = context
        )
    }*/

    /*@Singleton
    @Provides
    @UnauthenticatedHttpClient
    fun provideOwnApiOkHttpClient(
        datafastApiInterceptor: DatafastApiInterceptor
    ): OkHttpClient = ownApiHttpClientBuilder(datafastApiInterceptor).build()*/

    /*@Singleton
    @Provides
    @AuthenticatedHttpClient
    fun provideAuthenticatedOkHttpClient(
        datafastApiInterceptor: DatafastApiInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient = ownApiHttpClientBuilder(datafastApiInterceptor)
        .addInterceptor(authInterceptor)
        .build()

    @Provides
    @Singleton
    @SharedDatabase
    fun provideDatabase(@ApplicationContext context: Context): RoomDatabase =
        buildDatabase(context, DatafastDatabase::class.java, "Datafast")


     */
    private fun ownApiHttpClientBuilder(
        apiInterceptor: DatafastApiInterceptor
    ): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addInterceptor(apiInterceptor)
            .addInterceptor(DatafastLoggingInterceptor.interceptor)
}
