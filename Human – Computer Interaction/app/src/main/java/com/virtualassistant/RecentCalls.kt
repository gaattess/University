package com.virtualassistant

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.CallLog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RecentCalls : AppCompatActivity() {
    private val PERMISSIONS_REQUEST_CODE = 1
    private val permission = arrayOf(Manifest.permission.READ_CALL_LOG)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (permission.any {
                ContextCompat.checkSelfPermission(
                    this,
                    it
                ) != PackageManager.PERMISSION_GRANTED
            }) {
            ActivityCompat.requestPermissions(this, permission, PERMISSIONS_REQUEST_CODE)
        } else {
            // You already have permission
            setContentView(R.layout.recentcalls_recyclerview)
            val recentCallsList = getRecentCalls()

            if (recentCallsList.isEmpty()) {
                // There are no recent calls
                Toast.makeText(this, "No recent calls to display", Toast.LENGTH_SHORT).show()
            } else {
                val viewManager = LinearLayoutManager(this)
                val viewAdapter = RecentCallsAdapter(recentCallsList, this)
                val recyclerView = findViewById<RecyclerView>(R.id.recentCallsRecyclerView).apply {
                    setHasFixedSize(true)
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
            }
        }
    }

    fun getRecentCalls(): List<CallsLog> {
        val calls = mutableListOf<CallsLog>()
        val cursor = contentResolver.query(
            CallLog.Calls.CONTENT_URI,
            null,
            null,
            null,
            CallLog.Calls.DATE + " DESC"

        )
        cursor?.let {
            if (!it.moveToFirst()) {
                // There are no recent calls
                it.close()
                return emptyList()
            }

            do {
                val number = it.getString(it.getColumnIndex(CallLog.Calls.NUMBER))
                val date = it.getLong(it.getColumnIndex(CallLog.Calls.DATE))
                calls.add(CallsLog(number, date))
            } while (it.moveToNext())


            it.close()
        }
        return calls

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSIONS_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    // All permissions were granted, load the contacts
                    val recentCallsList = getRecentCalls()

                    if (recentCallsList.isEmpty()) {
                        // There are no recent calls
                        Toast.makeText(this, "No recent calls to display", Toast.LENGTH_SHORT).show()
                    } else {
                        val viewManager = LinearLayoutManager(this)
                        val viewAdapter = RecentCallsAdapter(recentCallsList, this)
                        val recyclerView = findViewById<RecyclerView>(R.id.recentCallsRecyclerView).apply {
                            setHasFixedSize(true)
                            layoutManager = viewManager
                            adapter = viewAdapter
                        }
                    }
                } else {
                    // Show a message to the user explaining why the app needs the permission
                    Toast.makeText(
                        this,
                        "This app requires certain permissions to function properly.",
                        Toast.LENGTH_LONG
                    ).show()

                    // Close the app
                    finish()
                }
                return
            }

            else -> {
                // Ignore all other requests.
            }
        }

    }
}


data class CallsLog(
    val number: String,
    val date: Long,
)
