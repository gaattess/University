package com.virtualassistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessagesAdapter(private val smsList: List<SMS>) :
    RecyclerView.Adapter<MessagesAdapter.SMSViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SMSViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.messages_read_card, parent, false)
        return SMSViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SMSViewHolder, position: Int) {
        val sms = smsList[position]
        holder.senderTextView.text = sms.sender
        holder.messageTextView.text = sms.message
    }

    override fun getItemCount(): Int {
        return smsList.size
    }

    class SMSViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderTextView: TextView = itemView.findViewById(R.id.senderTextView)
        val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
    }
}
