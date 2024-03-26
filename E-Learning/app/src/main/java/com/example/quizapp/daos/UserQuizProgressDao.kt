package com.example.quizapp.daos

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quizapp.entities.UserQuizProgressEntity

@Dao
interface UserQuizProgressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertOne(progress: UserQuizProgressEntity)

    fun markAsCompletedBlocking(
        userId: Int,
        questionId: Int,
        answeredCorrectly: Boolean,
    ) {
        Log.i("TAG", "Marking $questionId as correct=$answeredCorrectly")
        val progress = UserQuizProgressEntity(userId, questionId, answeredCorrectly)
        insertOne(progress)
    }

    @Query(
        """
select 
    count(*) as answered_correctly
from 
    question                 q
left join user_quiz_progress uqp on 
	q.id = uqp.question_id
where q.chapter = :chapter
   and COALESCE(uqp.user_id, :userId) = :userId
   and COALESCE(uqp.answered_correct, 0) = 1;
    """
    )
    fun getNumberOfCorrectInChapter(userId: Int, chapter: Int): Int

    @Query(
        """
delete from user_quiz_progress
 where user_id = :userId 
   and question_id in (
        select q.id 
          from question q 
         where q.chapter = :chapter
   )
"""
    )
    fun resetChapter(userId: Int, chapter: Int)
}
