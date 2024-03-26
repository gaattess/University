package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TheorySelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.theory_selection_activity)

        findViewById<Button>(R.id.k1ButtonT).setOnClickListener {
            val k1Intent = Intent(this, TheoryActivity::class.java)

            // Create a Bundle to pass data to the TheoryActivity
            val bundle = Bundle()
            bundle.putInt("buttonId", R.id.k1ButtonT)
            k1Intent.putExtras(bundle)

            startActivity(k1Intent)
        }

        findViewById<Button>(R.id.k2ButtonT).setOnClickListener {
            val k2Intent = Intent(this, TheoryActivity::class.java)

            // Create a Bundle to pass data to the TheoryActivity
            val bundle = Bundle()
            bundle.putInt("buttonId", R.id.k2ButtonT)
            k2Intent.putExtras(bundle)

            startActivity(k2Intent)
        }

        findViewById<Button>(R.id.k3ButtonT).setOnClickListener {
            val k3Intent = Intent(this, TheoryActivity::class.java)

            // Create a Bundle to pass data to the TheoryActivity
            val bundle = Bundle()
            bundle.putInt("buttonId", R.id.k3ButtonT)
            k3Intent.putExtras(bundle)

            startActivity(k3Intent)
        }

        findViewById<Button>(R.id.k4ButtonT).setOnClickListener {
            val k4Intent = Intent(this, TheoryActivity::class.java)

            // Create a Bundle to pass data to the TheoryActivity
            val bundle = Bundle()
            bundle.putInt("buttonId", R.id.k4ButtonS)
            k4Intent.putExtras(bundle)

            startActivity(k4Intent)
        }

        findViewById<Button>(R.id.k5ButtonT).setOnClickListener {
            val k5Intent = Intent(this, TheoryActivity::class.java)

            // Create a Bundle to pass data to the TheoryActivity
            val bundle = Bundle()
            bundle.putInt("buttonId", R.id.k5ButtonT)
            k5Intent.putExtras(bundle)

            startActivity(k5Intent)
        }

        findViewById<Button>(R.id.k6ButtonT).setOnClickListener {
            val k6Intent = Intent(this, TheoryActivity::class.java)

            // Create a Bundle to pass data to the TheoryActivity
            val bundle = Bundle()
            bundle.putInt("buttonId", R.id.k6ButtonS)
            k6Intent.putExtras(bundle)

            startActivity(k6Intent)
        }

        findViewById<Button>(R.id.k7ButtonT).setOnClickListener {
            val k7Intent = Intent(this, TheoryActivity::class.java)

            // Create a Bundle to pass data to the TheoryActivity
            val bundle = Bundle()
            bundle.putInt("buttonId", R.id.k7ButtonS)
            k7Intent.putExtras(bundle)

            startActivity(k7Intent)
        }

        findViewById<Button>(R.id.k8ButtonT).setOnClickListener {
            val k8Intent = Intent(this, TheoryActivity::class.java)

            // Create a Bundle to pass data to the TheoryActivity
            val bundle = Bundle()
            bundle.putInt("buttonId", R.id.k8ButtonS)
            k8Intent.putExtras(bundle)

            startActivity(k8Intent)
        }

        findViewById<Button>(R.id.r1ButtonT).setOnClickListener {
            val r1Intent = Intent(this, TheoryActivity::class.java)

            // Create a Bundle to pass data to the TheoryActivity
            val bundle = Bundle()
            bundle.putInt("buttonId", R.id.r1ButtonT)
            r1Intent.putExtras(bundle)

            startActivity(r1Intent)
        }

        findViewById<Button>(R.id.r2ButtonT).setOnClickListener {
            val r2Intent = Intent(this, TheoryActivity::class.java)

            // Create a Bundle to pass data to the TheoryActivity
            val bundle = Bundle()
            bundle.putInt("buttonId", R.id.r2ButtonT)
            r2Intent.putExtras(bundle)

            startActivity(r2Intent)
        }

    }
}