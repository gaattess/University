package com.virtualassistant

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(private val contacts: List<Contact>, private val ctx: Context) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val first: TextView = itemView.findViewById(R.id.nameText)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contacts_card, parent, false)
        Log.i("TAG", contacts.toString())


        return ViewHolder(view)
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // This method retrieves data and displays inside the view (i.e. Card) while binding
        holder.first.text = contacts[position].name
    }
}