package com.example.quizapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_quiz_progress",
    indices = [Index(value = ["user_id", "question_id"], unique = true)]
)
class UserQuizProgressEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "question_id") val questionId: Int,
    @ColumnInfo(name = "answered_correct") val answeredCorrect: Boolean
) {
    constructor(
        userId: Int,
        questionId: Int,
        answeredCorrect: Boolean
    ) : this(0, userId, questionId, answeredCorrect)
}
