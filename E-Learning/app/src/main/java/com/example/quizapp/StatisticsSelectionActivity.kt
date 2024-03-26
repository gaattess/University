package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StatisticsSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statistics_selection_activity)

        listOf(
                R.id.k1ButtonS,
                R.id.k2ButtonS,
                R.id.k3ButtonS,
                R.id.k4ButtonS,
                R.id.k5ButtonS,
                R.id.k6ButtonS,
                R.id.k7ButtonS,
                R.id.k8ButtonS
            )
            .forEachIndexed { i, btnId ->
                findViewById<Button>(btnId).setOnClickListener { startStatsForChapter(i + 1) }
            }
    }

    private fun startStatsForChapter(chapter: Int) {
        val intent = Intent(this, StatisticsActivity::class.java).putExtra("chapter", chapter)
        startActivity(intent)
    }
}
