package com.example.quizapp.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizapp.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    fun getByEmail(email: String): UserEntity?

    @Insert fun insertOne(user: UserEntity)
}
