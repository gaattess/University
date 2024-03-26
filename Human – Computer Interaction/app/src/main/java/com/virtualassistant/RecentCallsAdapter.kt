package com.virtualassistant

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RecentCallsAdapter(private val calls: List<CallsLog>, private val ctx: Context) :
    RecyclerView.Adapter<RecentCallsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val callerName: TextView = itemView.findViewById(R.id.callerName)
        val callerDate: TextView = itemView.findViewById(R.id.callerDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recentcalls_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = calls.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // This method retrieves data and displays inside the view (i.e. Card) while binding
        holder.callerName.text = calls[position].number
        // Get the date in milliseconds
        val dateInMillis = calls[position].date

        // Convert it to a Date object
        val date = Date(dateInMillis)

        // Format the date into a String
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val dateString = formatter.format(date)

        // Set the formatted date String to your TextView
        holder.callerDate.text = dateString
    }
}