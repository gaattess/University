package com.virtualassistant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.serialization.json.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

private val TAG = "MainActivity"

class Weather : AppCompatActivity() {

    private var textView: TextView? = null

    private val API_KEY = "06e061f911e516b793ec0ff035d7e1d1"
    private val client = OkHttpClient()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_activity)

        val userInput: EditText = findViewById(R.id.locationInsterText)
        val button: Button = findViewById(R.id.weatherButton)
        textView = findViewById(R.id.weatherTextView)
        textView?.text = ""
        textView?.movementMethod = ScrollingMovementMethod()
        userInput.text.clear()

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val city = userInput.text.toString()
                userInput.text.clear()
                textView?.text = ""
                GetWeatherTask().execute(city)
            }
        })
    }

    inner class GetWeatherTask : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String): String? {
            val city = params[0]
            val request = Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$API_KEY&units=metric")
                .build()

            return try {
                client.newCall(request).execute().use { response ->
                    val jsonElement: JsonElement? = response.body?.string()
                        ?.let { Json.parseToJsonElement(it) }

                    val temp = jsonElement?.jsonObject?.get("main")?.jsonObject?.get("temp")?.jsonPrimitive?.content ?: "N/A"
                    val humidity = jsonElement?.jsonObject?.get("main")?.jsonObject?.get("humidity")?.jsonPrimitive?.content ?: "N/A"
                    val windSpeed = jsonElement?.jsonObject?.get("wind")?.jsonObject?.get("speed")?.jsonPrimitive?.content ?: "N/A"
                    val windDeg = jsonElement?.jsonObject?.get("wind")?.jsonObject?.get("deg")?.jsonPrimitive?.content ?: "N/A"
                    val description = jsonElement?.jsonObject?.get("weather")?.jsonArray?.get(0)?.jsonObject?.get("description")?.jsonPrimitive?.content ?: "N/A"

                    "Temperature in $city is $temp°C\n" +
                            "Humidity is $humidity%\n" +
                            "Wind speed is $windSpeed m/s\n" +
                            "Wind direction is $windDeg°\n" +
                            "Description: $description"
                }
            } catch (e: IOException) {
                Log.e(TAG, "Error making network request", e)
                null
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            result?.let { textView?.append("$it\n\n") }
        }
    }
}