package com.virtualassistant

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RemoteMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.remote_main_view_activity)

        val lights = findViewById<TextView>(R.id.lightsTextView)
        val ac = findViewById<TextView>(R.id.airconditionTextView)

        lights.setOnClickListener {
            val lightsIntent = Intent(this, RemoteLights::class.java)
            startActivity(lightsIntent)
        }

        ac.setOnClickListener {
            val acIntent = Intent(this, RemoteAc::class.java)
            startActivity(acIntent)
        }
    }



}