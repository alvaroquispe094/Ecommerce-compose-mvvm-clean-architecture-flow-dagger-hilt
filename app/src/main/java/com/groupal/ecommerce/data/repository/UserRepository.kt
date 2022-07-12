package com.groupal.ecommerce.data.repository

import com.groupal.ecommerce.data.database.dao.UsersDao
import com.groupal.ecommerce.data.database.entity.UserEntity
import javax.inject.Inject

class UserRepository  @Inject constructor(
    private val userDao: UsersDao
) {

    suspend fun getLogin(user: String, password: String): UserEntity {
        return userDao.readLoginData(user, password)
    }

//    suspend fun getAllQuotesFromDatabase():List<Quote>{
//        val response: List<UserEntity> = UserDao.getAllQuotes()
//        return response.map { it.toDomain() }
//    }
//
//    suspend fun insertQuotes(quotes:List<QuoteEntity>){
//        quoteDao.insertAll(quotes)
//    }
//
//    suspend fun clearQuotes(){
//        quoteDao.deleteAllQuotes()
//    }
}