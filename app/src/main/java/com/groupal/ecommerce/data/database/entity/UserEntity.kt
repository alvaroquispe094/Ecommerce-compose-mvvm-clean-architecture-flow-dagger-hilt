package com.groupal.ecommerce.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.groupal.ecommerce.data.database.DbConstant
import com.groupal.ecommerce.data.remote.dto.ProductDto
import com.groupal.ecommerce.domain.model.Product
import com.groupal.ecommerce.domain.model.User

@Entity(tableName = DbConstant.USER_TABLE)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    var mobNum: String,
    var password: String,
    var name:String,
    var profession:String,
    var email:String,
)

// Just like a mapper
fun UserEntity.toUser(): User {
    return User(
        id = id,
        mobNum = mobNum,
        password = password,
        name = name,
        profession = profession,
        email = email
    )
}