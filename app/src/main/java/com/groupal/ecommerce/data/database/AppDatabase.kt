package com.groupal.ecommerce.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.groupal.ecommerce.data.database.dao.UsersDao
import com.groupal.ecommerce.data.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UsersDao
}