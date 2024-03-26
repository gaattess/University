package com.virtualassistant

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MessagesRead : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var smsAdapter: MessagesAdapter
    private lateinit var smsList: MutableList<SMS>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.messages_read_recyclerview)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        smsList = mutableListOf()
        smsAdapter = MessagesAdapter(smsList)
        recyclerView.adapter = smsAdapter

        // Request READ_SMS permission if not granted already
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_SMS),
                PERMISSION_REQUEST_CODE
            )
        } else {
            // Permission already granted, read SMS
            readSMS()
        }
    }

    private fun readSMS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_SMS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val cursor = contentResolver.query(
                    Telephony.Sms.Inbox.CONTENT_URI,
                    null,
                    null,
                    null,
                    null
                )

                cursor?.use { cursor ->
                    val indexBody = cursor.getColumnIndexOrThrow(Telephony.Sms.BODY)
                    val indexAddress = cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS)

                    while (cursor.moveToNext()) {
                        val smsBody = cursor.getString(indexBody)
                        val address = cursor.getString(indexAddress)

                        val sms = SMS(address, smsBody)
                        smsList.add(sms)
                    }
                }

                smsAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, read SMS
                readSMS()
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 123
    }
}

data class SMS(val sender: String, val message: String)
