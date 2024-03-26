package com.example.quizapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user", indices = [Index(value=["email"], unique = true)])
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val email: String,
    val username: String,
    @ColumnInfo(name = "password_hash") val passwordHash: String,
) {
    constructor(email: String, username: String, passwordHash: String): this(0, email, username, passwordHash)
}
