package com.example.quizapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answer_fill")
class AnswerFillEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "question_id") val questionId: Int,
    val text: String,
    val order: Int,
)
