package com.virtualassistant

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class Email : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.email_activity)

        textToSpeech = TextToSpeech(this, this)

        val sendEmailButton: Button = findViewById(R.id.sendEmailButton)
        val readEmailButton: Button = findViewById(R.id.readEmailButton)

        sendEmailButton.setOnClickListener {
            speakOut(sendEmailButton.text.toString())
            val intent = Intent(this, EmailSend::class.java)
            startActivity(intent)
        }

        readEmailButton.setOnClickListener {
            speakOut(readEmailButton.text.toString())
            val intent = Intent(this, EmailRead::class.java)
            startActivity(intent)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                showError("Language not supported")
            }
        } else {
            showError("TextToSpeech initialization failed")
        }
    }

    private fun speakOut(text: String) {
        if (textToSpeech.isSpeaking) {
            textToSpeech.stop()
        }
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.stop()
        textToSpeech.shutdown()
    }

    private fun showError(message: String = "An error occurred") {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
