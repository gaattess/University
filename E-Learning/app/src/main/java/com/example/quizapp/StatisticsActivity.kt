package com.example.quizapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StatisticsActivity : AppCompatActivity() {
    private lateinit var chapterText: TextView
    private lateinit var answersText: TextView
    private lateinit var pieChart: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statistics_activity)

        bindWidgets()
        val chapter = intent.getIntExtra("chapter", 1)
        getStats(chapter)
    }

    @SuppressLint("SetTextI18n")
    private fun getStats(chapter: Int) {
        val db = AppDatabase.getDatabase(this)
        val progressDao = db.userQuizProgressDao()

        val answeredCorrectly =
            progressDao.getNumberOfCorrectInChapter(LoggedInUserId.userId!!, chapter)
        val totalQuestions =
            if (chapter == 8) {
                7
            } else {
                10
            }

        chapterText.text = "Στατιστικά κεφαλαίου $chapter"
        answersText.text = "$answeredCorrectly / $totalQuestions"
        val d = answeredCorrectly / totalQuestions
        val progress = d * 100
        pieChart.progress = progress
    }

    private fun bindWidgets() {
        chapterText = findViewById(R.id.chapterStatstextView)
        answersText = findViewById(R.id.answersTextView)
        pieChart = findViewById(R.id.stats_progressbar)
    }
}
