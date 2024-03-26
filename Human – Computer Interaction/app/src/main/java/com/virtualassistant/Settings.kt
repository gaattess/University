package com.virtualassistant

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class Settings : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        // Button for opening the brightness settings
        val btnBrightness = findViewById<Button>(R.id.btnBrightness)
        btnBrightness.setOnClickListener {
            openDisplaySettings()
        }

        // Button for opening the airplane mode settings
        val btnAirplaneMode = findViewById<Button>(R.id.btnAirplaneMode)
        btnAirplaneMode.setOnClickListener {
            openAirplaneModeSettings()
        }

        // Button for opening the auto-rotate settings
        val btnAutoRotate = findViewById<Button>(R.id.btnAutoRotate)
        btnAutoRotate.setOnClickListener {
            openAutoRotateSettings()
        }

        // Button for opening the Wi-Fi settings
        val btnWiFi = findViewById<Button>(R.id.btnWiFi)
        btnWiFi.setOnClickListener {
            openWiFiSettings()
        }

        // Button for opening the sound settings
        val btnSound = findViewById<Button>(R.id.btnSound)
        btnSound.setOnClickListener {
            openSoundSettings()
        }

        // Button for opening the Bluetooth settings
        val btnBluetooth = findViewById<Button>(R.id.btnBluetooth)
        btnBluetooth.setOnClickListener {
            openBluetoothSettings()
        }

        // Button for opening the location settings
        val btnLocation = findViewById<Button>(R.id.btnLocation)
        btnLocation.setOnClickListener {
            openLocationSettings()
        }

        // Button for opening the data usage settings
        val btnDataUsage = findViewById<Button>(R.id.btnDataUsage)
        btnDataUsage.setOnClickListener {
            openDataUsageSettings()
        }

        // Button for opening the battery saver settings
        val btnBatterySaver = findViewById<Button>(R.id.btnBatterySaver)
        btnBatterySaver.setOnClickListener {
            openBatterySaverSettings()
        }
    }

    // Open the display settings
    private fun openDisplaySettings() {
        val intent = Intent(Settings.ACTION_DISPLAY_SETTINGS)
        startActivity(intent)
    }

    // Open the airplane mode settings
    private fun openAirplaneModeSettings() {
        val intent = Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS)
        startActivity(intent)
    }

    // Open the auto-rotate settings
    private fun openAutoRotateSettings() {
        val intent = Intent(Settings.ACTION_DISPLAY_SETTINGS)
        startActivity(intent)
    }

    // Open the Wi-Fi settings
    private fun openWiFiSettings() {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        startActivity(intent)
    }

    // Open the sound settings
    private fun openSoundSettings() {
        val intent = Intent(Settings.ACTION_SOUND_SETTINGS)
        startActivity(intent)
    }

    // Open the Bluetooth settings
    private fun openBluetoothSettings() {
        val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
        startActivity(intent)
    }

    // Open the location settings
    private fun openLocationSettings() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(intent)
    }

    // Open the data usage settings (requires API level 28 or above)
    @RequiresApi(Build.VERSION_CODES.P)
    private fun openDataUsageSettings() {
        val intent = Intent(Settings.ACTION_DATA_USAGE_SETTINGS)
        startActivity(intent)
    }

    // Open the battery saver settings
    private fun openBatterySaverSettings() {
        val intent = Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS)
        startActivity(intent)
    }
}
