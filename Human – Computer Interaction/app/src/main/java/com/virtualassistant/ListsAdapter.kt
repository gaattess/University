package com.virtualassistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ListsAdapter(private val todos: MutableList<TodoDataClass>) : RecyclerView.Adapter<ListsAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lists_card, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.titleEditText.setText(todo.title)
        holder.descriptionEditText.setText(todo.description)

        holder.updateButton.setOnClickListener {
            val updatedTitle = holder.titleEditText.text.toString()
            val updatedDescription = holder.descriptionEditText.text.toString()
            val updatedTodo = TodoDataClass(updatedTitle, updatedDescription)
            todos[position] = updatedTodo
            notifyDataSetChanged()
        }

        holder.deleteButton.setOnClickListener {
            todos.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val titleEditText: EditText = itemView.findViewById(R.id.titleEditText)
        val descriptionEditText: EditText = itemView.findViewById(R.id.descriptionEditText)
        val updateButton: Button = itemView.findViewById(R.id.updateButton)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }
}
