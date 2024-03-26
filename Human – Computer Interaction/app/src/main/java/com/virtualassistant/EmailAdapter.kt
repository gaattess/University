package com.virtualassistant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.virtualassistant.databinding.EmailReadCardBinding

class EmailAdapter : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {
    private val emails = mutableListOf<EmailDataClass>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val binding = EmailReadCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emails[position]
        holder.bind(email)
    }

    override fun getItemCount(): Int {
        return emails.size
    }

    fun setEmails(emails: List<EmailDataClass>) {
        this.emails.clear()
        this.emails.addAll(emails)
        notifyDataSetChanged()
    }

    inner class EmailViewHolder(private val binding: EmailReadCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(email: EmailDataClass) {
            binding.senderTextView.text = email.sender
            binding.subjectTextView.text = email.subject
            binding.messageTextView.text = email.message
        }
    }
}
