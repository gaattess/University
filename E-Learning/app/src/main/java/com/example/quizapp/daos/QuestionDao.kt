package com.example.quizapp.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.quizapp.entities.QuestionEntity

@Dao
interface QuestionDao {
    @Query("SELECT * FROM question where id = :id") fun get(id: Int): QuestionEntity?

    @Query(
        """
select * 
  from question
 where chapter = :chapter
   and id not in (
   select uqp.question_id
     from user_quiz_progress uqp
	where uqp.user_id = :userId
	)
order by id ASC
limit 1
    """
    )
    fun getNextInChapter(chapter: Int, userId: Int): QuestionEntity?
}
