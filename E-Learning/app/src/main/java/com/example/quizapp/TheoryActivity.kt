package com.example.quizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TheoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.theory_recyclerview)

        val buttonId = intent.extras?.getInt("buttonId", -1)

        val theoryList = getTheory(buttonId)

        val theoryAdapter = TheoryAdapter(theoryList, this)
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            adapter = theoryAdapter
            layoutManager = LinearLayoutManager(this@TheoryActivity)
        }

    }

    private fun getTheory(buttonId: Int?): List<TheoryImages> {
        val theoryList = mutableListOf<TheoryImages>()

        when (buttonId) {
            R.id.k1ButtonT -> {
                theoryList.add(TheoryImages(R.drawable.k1_p1))
                theoryList.add(TheoryImages(R.drawable.k1_p2))
                theoryList.add(TheoryImages(R.drawable.k1_p3))
            }

            R.id.k2ButtonT -> {
                theoryList.add(TheoryImages(R.drawable.k2_p1))
                theoryList.add(TheoryImages(R.drawable.k2_p2))
                theoryList.add(TheoryImages(R.drawable.k2_p3))
                theoryList.add(TheoryImages(R.drawable.k2_p4))
                theoryList.add(TheoryImages(R.drawable.k2_p5))
            }

            R.id.k3ButtonT -> {
                theoryList.add(TheoryImages(R.drawable.k3_p1))
                theoryList.add(TheoryImages(R.drawable.k3_p2))
                theoryList.add(TheoryImages(R.drawable.k3_p3))
            }

            R.id.k4ButtonS -> {
                theoryList.add(TheoryImages(R.drawable.k4_p1))
                theoryList.add(TheoryImages(R.drawable.k4_p2))
                theoryList.add(TheoryImages(R.drawable.k4_p3))
                theoryList.add(TheoryImages(R.drawable.k4_p4))
            }

            R.id.k5ButtonT -> {
                theoryList.add(TheoryImages(R.drawable.k5_p1))
                theoryList.add(TheoryImages(R.drawable.k5_p2))
                theoryList.add(TheoryImages(R.drawable.k5_p3))
                theoryList.add(TheoryImages(R.drawable.k5_p4))
                theoryList.add(TheoryImages(R.drawable.k5_p5))
                theoryList.add(TheoryImages(R.drawable.k5_p6))
            }

            R.id.k6ButtonS -> {
                theoryList.add(TheoryImages(R.drawable.k6_p1))
                theoryList.add(TheoryImages(R.drawable.k6_p2))
            }

            R.id.k7ButtonS -> {
                theoryList.add(TheoryImages(R.drawable.k7_p1))
                theoryList.add(TheoryImages(R.drawable.k7_p2))
                theoryList.add(TheoryImages(R.drawable.k7_p3))
                theoryList.add(TheoryImages(R.drawable.k7_p4))
            }

            R.id.k8ButtonS -> {
                theoryList.add(TheoryImages(R.drawable.k8_p1))
                theoryList.add(TheoryImages(R.drawable.k8_p2))
            }

            R.id.r1ButtonT -> {
                theoryList.add(TheoryImages(R.drawable.revision_k1_2_3_4_5))
            }

            R.id.r2ButtonT -> {
                theoryList.add(TheoryImages(R.drawable.revision_k6_7_8))
            }
        }

        return theoryList
    }

}

data class TheoryImages(val image: Int)