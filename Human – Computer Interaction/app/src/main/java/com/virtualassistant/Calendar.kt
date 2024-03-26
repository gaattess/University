package com.virtualassistant

import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Calendar : AppCompatActivity() {

    lateinit var date: TextView
    lateinit var calendar: CalendarView
    lateinit var textEvent: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)

        date = findViewById(R.id.currentDate)
        calendar = findViewById(R.id.calendarView)


        calendar.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
                // In this Listener we are getting values
                // such as year, month and day of month
                // on below line we are creating a variable
                // in which we are adding all the variables in it
                val countDate = (dayOfMonth.toString() + "-"
                        + (month + 1) + "-" + year)
                // set this date in TextView for Display
                date.setText(countDate)
            }
        )
    }
}
