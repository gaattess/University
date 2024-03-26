package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)
        mainButtons()
    }


    private fun mainButtons() {
        findViewById<CardView>(R.id.quizCard).setOnClickListener {
            val quizIntent = Intent(this, QuizSelectionActivity::class.java)
            startActivity(quizIntent)
        }

        findViewById<CardView>(R.id.theoryCard).setOnClickListener {
            val theoryIntent = Intent(this, TheorySelectionActivity::class.java)
            startActivity(theoryIntent)
        }

        findViewById<CardView>(R.id.statisticsCard).setOnClickListener {
            val statisticsIntent = Intent(this, StatisticsSelectionActivity::class.java)
            startActivity(statisticsIntent)
        }

        findViewById<CardView>(R.id.faqCard).setOnClickListener {
            val statisticsIntent = Intent(this, FAQActivity::class.java)
            startActivity(statisticsIntent)
        }

        findViewById<CardView>(R.id.logoutCard).setOnClickListener {
            val logoutIntent = Intent(this, MainActivity::class.java)
            startActivity(logoutIntent)
        }
    }
}
