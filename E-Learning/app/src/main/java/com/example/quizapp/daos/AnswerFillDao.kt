package com.example.quizapp.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.quizapp.entities.AnswerFillEntity

@Dao
interface AnswerFillDao {
    @Query("select * from answer_fill where question_id = :questionId")
    fun getForQuestion(questionId: Int): List<AnswerFillEntity>
}
