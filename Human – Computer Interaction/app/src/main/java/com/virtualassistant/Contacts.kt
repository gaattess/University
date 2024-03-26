package com.virtualassistant

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Contacts : AppCompatActivity() {
    val PERMISSIONS_REQUEST_CODE = 1
    private val permission = arrayOf(Manifest.permission.READ_CONTACTS)
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
            setContentView(R.layout.contacts_recyclerview)
            val contactList = getContacts()

            val viewManager = LinearLayoutManager(this)
            val viewAdapter = ContactsAdapter(contactList, this)
            val recyclerView = findViewById<RecyclerView>(R.id.contactsRecyclerView).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }
        }

    }


    @SuppressLint("Range")
    fun getContacts(): List<Contact> {
        val contacts = mutableListOf<Contact>()
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursor?.let {
            if (!it.moveToFirst()) {
                // There are no contacts
                it.close()
                return emptyList()
            }
            do {
                val name =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY))
                contacts.add(Contact(name))
            } while (it.moveToNext())

            it.close()
        }
        return contacts
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
                    val contactList = getContacts()

                    val viewManager = LinearLayoutManager(this)
                    val viewAdapter = ContactsAdapter(contactList, this)
                    val recyclerView = findViewById<RecyclerView>(R.id.contactsRecyclerView).apply {
                        setHasFixedSize(true)
                        layoutManager = viewManager
                        adapter = viewAdapter
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

data class Contact(val name: String)
