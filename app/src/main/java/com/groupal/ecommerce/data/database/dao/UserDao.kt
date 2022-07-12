package com.groupal.ecommerce.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.groupal.ecommerce.data.database.entity.UserEntity

@Dao
interface UsersDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertUser(users: UserEntity): Long
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertUserAll(users: List<UserEntity>): List<Long>

    @Query("SELECT * FROM User WHERE mobNum LIKE :mobNum AND password LIKE :password")
    fun readLoginData(mobNum: String, password: String):UserEntity

}