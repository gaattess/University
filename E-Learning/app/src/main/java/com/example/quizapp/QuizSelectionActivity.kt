package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_selection_activity)

        listOf(
                R.id.k1ButtonQ,
                R.id.k2ButtonQ,
                R.id.k3ButtonQ,
                R.id.k4ButtonQ,
                R.id.k5ButtonQ,
                R.id.k6ButtonQ,
                R.id.k7ButtonQ,
                R.id.k8ButtonQ
            )
            .forEachIndexed { i, btnId ->
                findViewById<Button>(btnId).setOnClickListener { goToChapterQuiz(i + 1) }
            }
    }

    private fun goToChapterQuiz(chapter: Int) {
        val qsDao = AppDatabase.getDatabase(this@QuizSelectionActivity).questionDao()
        val question = qsDao.getNextInChapter(chapter, LoggedInUserId.userId!!)

        if (question == null) {
            Toast.makeText(this, "Δεν βρέθηκαν ερωτήσεις", Toast.LENGTH_SHORT).show()
            return
        }

        val activity = getActivityForQuestionType(question.style)

        val intent =
            Intent(this, activity).putExtra("questionId", question.id).putExtra("chapter", chapter)
        startActivity(intent)
        finish()
    }
}
