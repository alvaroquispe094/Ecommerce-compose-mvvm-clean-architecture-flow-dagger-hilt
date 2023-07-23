package com.groupal.user.ecommerce.data

import android.content.Context
import com.groupal.shared.ecommerce.data.di.UnauthenticatedHttpClient
import com.groupal.shared.ecommerce.data.network.util.buildRetrofit
import com.groupal.user.ecommerce.data.network.AuthEndpoint
import com.groupal.user.ecommerce.data.network.UsersEndpoint
import com.groupal.user.ecommerce.data.repository.AuthLocalRepository
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
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object UserDataModule {
    private const val PROVIDE_RETROFIT_AUTH_API = "provideRetrofitAuthAPI"
    private const val PROVIDE_RETROFIT_USER_API = "provideRetrofitUserAPI"

    @Singleton
    @Provides
    @Named(PROVIDE_RETROFIT_AUTH_API)
    fun provideRetrofitAuthAPI(@UnauthenticatedHttpClient okHttpClient: OkHttpClient): Retrofit {
        // TODO: Parametrized URL
        return buildRetrofit(okHttpClient, "http://ecommerce-app-env.eba-xg323xbv.us-east-1.elasticbeanstalk.com/")
    }

    @Singleton
    @Provides
    @Named(PROVIDE_RETROFIT_USER_API)
    fun provideRetrofitUserAPI(@UnauthenticatedHttpClient okHttpClient: OkHttpClient): Retrofit {
        return buildRetrofit(
            okHttpClient,
            // TODO: Parametrized URL
            "http://ecommerce-app-env.eba-xg323xbv.us-east-1.elasticbeanstalk.com/"
        )
    }

    @Singleton
    @Provides
    fun provideUsersEndpoint(@Named(PROVIDE_RETROFIT_USER_API) retrofit: Retrofit): UsersEndpoint =
        retrofit.create(UsersEndpoint::class.java)

    @Singleton
    @Provides
    fun provideAuthEndpoint(
        @Named(PROVIDE_RETROFIT_AUTH_API) retrofit: Retrofit
    ): AuthEndpoint = retrofit.create(
        AuthEndpoint::class.java
    )

    @Singleton
    @Provides
    fun provideAuthRepository(authEndpoint: AuthEndpoint): IAuthRepository = AuthRepository(
        authEndpoint
    )

    @Singleton
    @Provides
    fun provideAuthLocalRepository(@ApplicationContext context: Context): IAuthLocalRepository =
        AuthLocalRepository(context)

    @Singleton
    @Provides
    fun provideUserRepository(
        userEndpoint: UsersEndpoint
    ): IUserRepository = UserRepository(userEndpoint)

    @Singleton
    @Provides
    fun provideAuthInterceptor(): AuthInterceptor2 = AuthInterceptor2()

    // Build Retrofit
   /* @Provides
    fun buildRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {

        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }*/
}
