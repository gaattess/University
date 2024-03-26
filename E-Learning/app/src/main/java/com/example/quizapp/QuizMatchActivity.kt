package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.daos.UserQuizProgressDao
import com.example.quizapp.entities.AnswerFillEntity
import com.example.quizapp.entities.QuestionEntity
import kotlin.properties.Delegates

class QuizMatchActivity : AppCompatActivity() {
    companion object {
        const val TAG = "quizApp"
    }

    private lateinit var questionTextView: TextView

    private lateinit var answerRowA: TextView
    private lateinit var answerRowB: TextView
    private lateinit var answerRowC: TextView
    private lateinit var answerRowD: TextView
    private lateinit var allAnswerRows: List<TextView>

    private lateinit var firstAnswer: EditText
    private lateinit var secondAnswer: EditText
    private lateinit var thirdAnswer: EditText
    private lateinit var fourthAnswer: EditText
    private lateinit var allAnswers: List<EditText>

    private lateinit var questionRowA: TextView
    private lateinit var questionRowB: TextView
    private lateinit var questionRowC: TextView
    private lateinit var questionRowD: TextView
    private lateinit var allQuestionRows: List<TextView>

    private var questionId by Delegates.notNull<Int>()
    private var chapter by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_match_activity)

        bindWidgets()
        limitAnswerAreas()

        questionId = intent.getIntExtra("questionId", 0)
        chapter = intent.getIntExtra("chapter", 0)

        val db = AppDatabase.getDatabase(this)

        val question = db.questionDao().get(questionId)
        if (question == null) {
            Log.e(TAG, "Question with id=$questionId not found")
            return
        }

        setQuestion(question)
        val answers = setAnswers(db, question.id)
        if (answers.isEmpty()) {
            Toast.makeText(this, "Ουπς, κάτι πήγε στραβά...", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, QuizSelectionActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.nextQuestionMatchButton).setOnClickListener {
            onNextQuestionCallback(db, answers)
        }
    }

    private fun onNextQuestionCallback(db: AppDatabase, answers: List<AnswerFillEntity>) {
        var isCorrect = true

        var i = 1
        for (answer in allAnswers) {
            val answerTxt = answer.text.toString()
            if (answerTxt.isBlank()) {
                // No selection made, ignore
                return
            }

            isCorrect =
                if (answerTxt == "i" && answers[0].order != i + 1) {
                    false
                } else if (answerTxt == "ii" && answers[1].order != i + 1) {
                    false
                } else if (answerTxt == "iii" && answers[2].order != i + 1) {
                    false
                } else if (answerTxt == "iv" && answers[3].order != i + 1) {
                    false
                } else {
                    // bad text
                    return
                }

            i++
        }

        db.userQuizProgressDao()
            .markAsCompletedBlocking(
                LoggedInUserId.userId!!,
                questionId,
                isCorrect,
            )

        val nextQuestion = db.questionDao().getNextInChapter(chapter, LoggedInUserId.userId!!)

        if (nextQuestion == null) {
            handleQuizEnd(db.userQuizProgressDao())
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

    private fun setQuestion(question: QuestionEntity) {
        val qTxt = question.title

        val delimiter = "<br>&emsp;"

        val lines = qTxt.split(delimiter)

        if (lines.size == 5) {
            // easy case
            var txt = formatText(lines[0] + delimiter)
            questionTextView.text = txt

            allQuestionRows.forEachIndexed { i, textView ->
                txt = formatText(lines[i + 1] + delimiter)
                textView.text = txt
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setAnswers(db: AppDatabase, questionId: Int): List<AnswerFillEntity> {
        val answers = db.answerFillDao().getForQuestion(questionId)
        if (answers.isEmpty()) {
            Log.e(TAG, "Found no answers for question=$questionId")
            return listOf()
        }

        val latin = listOf("i", "ii", "iii", "iv")
        answers.zip(allAnswerRows).forEachIndexed { i, (answer, txtView) ->
            txtView.text = "${latin[i]}) ${answer.text}"
        }

        return answers
    }

    private fun limitAnswerAreas() {
        val filter =
            object : InputFilter {
                override fun filter(
                    source: CharSequence?,
                    start: Int,
                    end: Int,
                    dest: Spanned?,
                    dstart: Int,
                    dend: Int
                ): CharSequence? {
                    val newLength = (dest?.length ?: 0) - (dend - dstart) + (end - start)
                    if (newLength <= 3) {
                        return null // Accept the input
                    }
                    return "" // Reject the input
                }
            }

        allAnswers.map { it.filters = arrayOf(filter) }
    }

    private fun bindWidgets() {
        questionTextView = findViewById(R.id.quizMatchQuestion)

        answerRowA = findViewById(R.id.textView15)
        answerRowB = findViewById(R.id.textView16)
        answerRowC = findViewById(R.id.textView17)
        answerRowD = findViewById(R.id.textView18)
        allAnswerRows = listOf(answerRowA, answerRowB, answerRowC, answerRowD)

        firstAnswer = findViewById(R.id.match1EditText)
        secondAnswer = findViewById(R.id.match2EditText)
        thirdAnswer = findViewById(R.id.match3EditText)
        fourthAnswer = findViewById(R.id.match4EditText)
        allAnswers = listOf(firstAnswer, secondAnswer, thirdAnswer, fourthAnswer)

        questionRowA = findViewById(R.id.textView11)
        questionRowB = findViewById(R.id.textView12)
        questionRowC = findViewById(R.id.textView13)
        questionRowD = findViewById(R.id.textView14)
        allQuestionRows = listOf(questionRowA, questionRowB, questionRowC, questionRowD)
    }
}
