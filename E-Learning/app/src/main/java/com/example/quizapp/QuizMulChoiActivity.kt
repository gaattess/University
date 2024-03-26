package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.daos.UserQuizProgressDao
import com.example.quizapp.entities.AnswerMultipleEntity
import com.example.quizapp.entities.QuestionEntity
import kotlin.properties.Delegates

class QuizActivity : AppCompatActivity() {
    companion object {
        const val TAG = "quizApp"
    }
    // Buttons
    private lateinit var radioGroup: RadioGroup
    private lateinit var questionText: TextView
    private lateinit var nextQuestionButton: Button

    private lateinit var answerBtn1: RadioButton
    private lateinit var answerBtn2: RadioButton
    private lateinit var answerBtn3: RadioButton
    private lateinit var answerBtn4: RadioButton
    private lateinit var answerButtons: List<RadioButton>

    private var questionId by Delegates.notNull<Int>()
    private var chapter by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_mulchoi_activity)

        bindWidgets()

        questionId = intent.getIntExtra("questionId", 0)
        chapter = intent.getIntExtra("chapter", 0)

        val db = AppDatabase.getDatabase(this)

        val question = db.questionDao().get(questionId)
        if (question == null) {
            Log.e(TAG, "Question with id=$questionId not found")
            return
        }

        questionText.text = formatText(question.title)

        val answers = setAnswers(db)
        if (answers.isEmpty()) {
            Toast.makeText(this, "Ουπς, κάτι πήγε στραβά...", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, QuizSelectionActivity::class.java)
            startActivity(intent)
            finish()
        }

        nextQuestionButton.setOnClickListener { onNextQuestionCallback(db, answers) }
    }

    private fun bindWidgets() {
        radioGroup = findViewById(R.id.radiogrpradioGroup)
        questionText = findViewById(R.id.question_text)
        nextQuestionButton = findViewById(R.id.nextQuestionButton)

        answerBtn1 = findViewById(R.id.radioButton1)
        answerBtn2 = findViewById(R.id.radioButton2)
        answerBtn3 = findViewById(R.id.radioButton3)
        answerBtn4 = findViewById(R.id.radioButton4)

        answerButtons = listOf(answerBtn1, answerBtn2, answerBtn3, answerBtn4)
    }

    private fun setAnswers(db: AppDatabase): List<AnswerMultipleEntity> {
        val answers = db.answerMultipleDao().getForQuestion(questionId)
        if (answers.isEmpty()) {
            Log.e(TAG, "Found no answers for question=$questionId")
            return listOf()
        }

        answers.zip(answerButtons).map { (answer, btn) ->
            btn.visibility = VISIBLE
            btn.text = formatText(answer.answer)
        }

        return answers
    }

    private fun onNextQuestionCallback(db: AppDatabase, answers: List<AnswerMultipleEntity>) {
        val selectedAnswerBtn = radioGroup.checkedRadioButtonId
        if (selectedAnswerBtn == -1) {
            // No selection made, ignore
            return
        }

        val selectedOptionIndex = radioGroup.indexOfChild(findViewById(selectedAnswerBtn))

        val selectedAnswer = answers[selectedOptionIndex]

        val progressDao = db.userQuizProgressDao()
        val questionDao = db.questionDao()

        progressDao.markAsCompletedBlocking(
            LoggedInUserId.userId!!,
            questionId,
            selectedAnswer.isCorrect,
        )
        val nextQuestion = questionDao.getNextInChapter(chapter, LoggedInUserId.userId!!)

        if (nextQuestion == null) {
            handleQuizEnd(progressDao)
        } else {
            goToNextQuestion(nextQuestion)
        }
    }

    private fun handleQuizEnd(progressDao: UserQuizProgressDao) {
        val answeredCorrectly =
            progressDao.getNumberOfCorrectInChapter(LoggedInUserId.userId!!, chapter)
        val passed = answeredCorrectly >= 8

        if (!passed) {
            progressDao.resetChapter(LoggedInUserId.userId!!, chapter)
        }

        val quizEndDialog = QuizCompletionFragment(passed, this)
        quizEndDialog.show(supportFragmentManager, "quizEndDialog")
    }

    private fun goToNextQuestion(nextQuestion: QuestionEntity) {
        val activity = getActivityForQuestionType(nextQuestion.style)
        val intent =
            Intent(this, activity)
                .putExtra("questionId", nextQuestion.id)
                .putExtra("chapter", chapter)
        startActivity(intent)
        finish()
    }
}
