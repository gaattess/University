package com.example.quizapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answer_multiple")
class AnswerMultipleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "question_id") val questionId: Int,
    val answer: String,
    @ColumnInfo(name = "is_correct") val isCorrect: Boolean
)
