package com.example.quizapp

import android.text.Html
import android.text.Spanned
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.entities.QuestionType

fun formatText(raw: String): Spanned? {
    return Html.fromHtml(raw, Html.FROM_HTML_MODE_COMPACT)
}

fun getActivityForQuestionType(type: QuestionType): Class<out AppCompatActivity> {
    return when (type) {
        QuestionType.MULTIPLE_CHOICE -> QuizActivity::class.java
        QuestionType.FILL_IN -> QuizMatchActivity::class.java
    }
}
