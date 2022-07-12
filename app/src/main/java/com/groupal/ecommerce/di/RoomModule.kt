package com.groupal.ecommerce.di

import android.content.Context
import androidx.room.Room
import com.groupal.ecommerce.data.database.AppDatabase
import com.groupal.ecommerce.data.database.DbConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOTE_DATABASE_NAME = "quote_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DbConstant.DB_NAME).build()

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()
}