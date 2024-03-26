package com.example.quizapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class QuestionType {
  MULTIPLE_CHOICE,
  FILL_IN,
}

@Entity(tableName = "question")
class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val style: QuestionType,
    val chapter: Int
)
