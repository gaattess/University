package com.virtualassistant

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class Traffic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.traffic_activity)

        val start: EditText = findViewById(R.id.address1Text)
        val end: EditText = findViewById(R.id.address2Text)
        val button: Button = findViewById(R.id.findTrafficButton)
        val result: TextView = findViewById(R.id.trafficResultsText)

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val random = Random(System.currentTimeMillis())
                val randomNumber = random.nextInt(15, 31) // Generates a random number between 15 (inclusive) and 31 (exclusive)
                if (randomNumber <= 20) {
                    result.text = "There isn't a lot of traffic.\nYou will arrive at your destination in $randomNumber minutes."
                } else if (randomNumber <= 25) {
                    result.text = "There is moderate traffic.\nYou will arrive at your destination in $randomNumber minutes."
                } else {
                    result.text = "There is a lot of traffic.\nYou will arrive at your destination in $randomNumber minutes."
                }
            }
        })

    }

}