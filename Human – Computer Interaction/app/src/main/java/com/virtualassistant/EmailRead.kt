package com.virtualassistant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.virtualassistant.databinding.EmailReadRecycleviewBinding

data class EmailDataClass(val sender: String, val subject: String, val message: String)

class EmailRead : AppCompatActivity() {
    private lateinit var binding: EmailReadRecycleviewBinding
    private lateinit var adapter: EmailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmailReadRecycleviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView
        adapter = EmailAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Populate the inbox with emails
        val emails = generateEmails()
        adapter.setEmails(emails)
    }

    private fun generateEmails(): List<EmailDataClass> {
        // Replace this with your own logic to fetch emails from a data source
        val emails = mutableListOf<EmailDataClass>()
        emails.add(EmailDataClass("John Doe", "Hello", "Lorem ipsum dolor sit amet..."))
        emails.add(EmailDataClass("Jane Smith", "Meeting", "Lorem ipsum dolor sit amet..."))
        emails.add(EmailDataClass("Alex Johnson", "Reminder", "Lorem ipsum dolor sit amet..."))
        return emails
    }
}
