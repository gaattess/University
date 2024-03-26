package com.virtualassistant

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RemoteAc : AppCompatActivity() {
    var totalTemperature = 0
    var modeCounter = 0
    var speedCounter = 0
    var directionCounter = 0

    lateinit var modeView: TextView
    lateinit var speedView: TextView
    lateinit var directionView: TextView
    lateinit var totalTemperatureView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.remote_ac_activity)

        // Restore saved state from savedInstanceState
        if (savedInstanceState != null) {
            totalTemperature = savedInstanceState.getInt("totalTemperature", 0)
            modeCounter = savedInstanceState.getInt("modeCounter", 0)
            speedCounter = savedInstanceState.getInt("speedCounter", 0)
            directionCounter = savedInstanceState.getInt("directionCounter", 0)

            modeView.visibility = savedInstanceState.getInt("modeViewVisibility", View.VISIBLE)
            speedView.visibility =
                savedInstanceState.getInt("speedViewVisibility", View.VISIBLE)
            directionView.visibility =
                savedInstanceState.getInt("directionViewVisibility", View.VISIBLE)
            totalTemperatureView.visibility =
                savedInstanceState.getInt("totalTemperatureViewVisibility", View.VISIBLE)

        }

        val power = findViewById<Button>(R.id.powerButton)
        val mode = findViewById<Button>(R.id.modeButton)
        val speed = findViewById<Button>(R.id.speedButton)
        val direction = findViewById<Button>(R.id.directionButton)
        val lowerTemp = findViewById<Button>(R.id.lowerTempButton)
        val higherTemp = findViewById<Button>(R.id.higherTempButton)

        modeView = findViewById(R.id.modeTextView)
        speedView = findViewById(R.id.speedTextView)
        directionView = findViewById(R.id.directionTextView)
        totalTemperatureView = findViewById(R.id.temperatureTextView)

        /*
        Make views be invisible till you press the power button
         */
        modeView.visibility = View.INVISIBLE
        speedView.visibility = View.INVISIBLE
        directionView.visibility = View.INVISIBLE
        totalTemperatureView.visibility = View.INVISIBLE

        power.setOnClickListener {
            // Toggle the visibility of the TextView.
            if (modeView.visibility == View.VISIBLE) {
                modeView.visibility = View.INVISIBLE
                speedView.visibility = View.INVISIBLE
                directionView.visibility = View.INVISIBLE
                totalTemperatureView.visibility = View.INVISIBLE
            } else {
                modeView.visibility = View.VISIBLE
                speedView.visibility = View.VISIBLE
                directionView.visibility = View.VISIBLE
                totalTemperatureView.visibility = View.VISIBLE
            }


            /*
            Changing the temperature
             */
            try {
                totalTemperature = totalTemperatureView.text.toString().toInt()
            } catch (e: NumberFormatException) {
                Log.e("NumberFormat", "Unable to parse integer from TextView text", e)
            }

            lowerTemp.setOnClickListener {
                totalTemperature -= 1
                totalTemperatureView.text = totalTemperature.toString()
            }

            higherTemp.setOnClickListener {
                totalTemperature += 1
                totalTemperatureView.text = totalTemperature.toString()
            }

            /*
            Changing the modes
             */
            val modeList = listOf("Auto", "Heating", "Fan", "Dehumidifying", "Cooling")

            mode.setOnClickListener {
                // Set the text of the modeView to the current mode.
                modeView.text = modeList[modeCounter]

                // Increase the modeCounter by one, or reset it to 0 if it's reached the end of the list.
                modeCounter = (modeCounter + 1) % modeList.size
            }

            /*
            Changing the speed
             */
            val speedList = listOf("Auto", "High", "Normal", "Low", "Quiet")

            speed.setOnClickListener {
                speedView.text = speedList[speedCounter]
                speedCounter = (speedCounter + 1) % speedList.size
            }

            /*
            Changing the direction
             */
            val directionList = listOf("Up", "Middle", "Down")


            direction.setOnClickListener {
                directionView.text = directionList[directionCounter]
                directionCounter = (directionCounter + 1) % directionList.size
            }


        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("totalTemperature", totalTemperature)
        outState.putInt("modeCounter", modeCounter)
        outState.putInt("speedCounter", speedCounter)
        outState.putInt("directionCounter", directionCounter)

        outState.putInt("modeViewVisibility", modeView.visibility)
        outState.putInt("speedViewVisibility", speedView.visibility)
        outState.putInt("directionViewVisibility", directionView.visibility)
        outState.putInt("totalTemperatureViewVisibility", totalTemperatureView.visibility)
    }
}

