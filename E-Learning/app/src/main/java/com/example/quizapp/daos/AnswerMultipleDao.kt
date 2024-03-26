package com.example.quizapp.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.quizapp.entities.AnswerMultipleEntity

@Dao
interface AnswerMultipleDao {
    @Query("select * from answer_multiple where question_id = :questionId")
    fun getForQuestion(questionId: Int): List<AnswerMultipleEntity>
}
