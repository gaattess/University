package com.virtualassistant


import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        widgets()


    }

    private fun widgets() {
        // Συνδέουμε τα widgets με μεταβλητές για την χρήση τους
        val calendarIconMain = findViewById<ImageView>(R.id.calendarIcon)
        val emailIconMain = findViewById<ImageView>(R.id.emailIcon)
        val alarmIconMain = findViewById<ImageView>(R.id.alarmIcon)
        val callsIconMain = findViewById<ImageView>(R.id.callsIcon)
        val contactsIconMain = findViewById<ImageView>(R.id.contactsIcon)
        val weatherIconMain = findViewById<ImageView>(R.id.weatherIcon)
        val trafficIconMain = findViewById<ImageView>(R.id.trafficIcon)
        val newsIconMain = findViewById<ImageView>(R.id.newsIcon)
        val listsIconMain = findViewById<ImageView>(R.id.listsIcon)
        val remoteIconMain = findViewById<ImageView>(R.id.remoteIcon)
        val settingsIconMain = findViewById<ImageButton>(R.id.settingButton)
        val infoIconMain = findViewById<ImageButton>(R.id.infoButton)
        val messagesIconMain = findViewById<ImageView>(R.id.messagesIcon)

        /*
        Χρησιμοποιούμε on Click Listeners για να μεταβούμε με το πάτημα του εικονιδίου από ένα activity σε άλλο
         */
        alarmIconMain.setOnClickListener {
            // Χρησιμοποιούμε intents για να μεταβούμε από ένα activity σε ένα άλλο με το πάτημα ενός widget
            val alarmIntent = Intent(this, Clock::class.java)
            // Ξεκίνημα των άλλων activities
            startActivity(alarmIntent)
        }

        callsIconMain.setOnClickListener {
            val recentCallsIntent = Intent(this, RecentCalls::class.java)
            startActivity(recentCallsIntent)
        }

        remoteIconMain.setOnClickListener {
            val remoteIntent = Intent(this, RemoteMain::class.java)
            startActivity(remoteIntent)
        }

        trafficIconMain.setOnClickListener {
            val trafficIntent = Intent(this, Traffic::class.java)
            startActivity(trafficIntent)
        }

        weatherIconMain.setOnClickListener {
            val weatherIntent = Intent(this, Weather::class.java)
            startActivity(weatherIntent)
        }

        calendarIconMain.setOnClickListener {
            val calendarIntent = Intent(this, Calendar::class.java)
            startActivity(calendarIntent)
        }

        contactsIconMain.setOnClickListener {
            val contactsIntent = Intent(this, Contacts::class.java)
            startActivity(contactsIntent)
        }

        listsIconMain.setOnClickListener {
            val listsIntent = Intent(this, Lists::class.java)
            startActivity(listsIntent)
        }

        newsIconMain.setOnClickListener {
            val newsIntent = Intent(this, News::class.java)
            startActivity(newsIntent)
        }

        settingsIconMain.setOnClickListener {
            val settingsIntent = Intent(this, Settings::class.java)
            startActivity(settingsIntent)
        }

        infoIconMain.setOnClickListener {
            val infoIntent = Intent(this, Info::class.java)
            startActivity(infoIntent)
        }
        emailIconMain.setOnClickListener {
            val emailIntent = Intent(this, Email::class.java)
            startActivity(emailIntent)
        }

        messagesIconMain.setOnClickListener {
            val messagesIntent = Intent(this, Messages::class.java)
            startActivity(messagesIntent)
        }
    }
}