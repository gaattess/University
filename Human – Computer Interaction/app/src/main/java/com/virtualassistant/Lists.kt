package com.virtualassistant

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Lists : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var todoAdapter: ListsAdapter
    private lateinit var addTodoButton: Button
    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private var todos = mutableListOf<TodoDataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lists_recyclerview)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        todoAdapter = ListsAdapter(todos)
        recyclerView.adapter = todoAdapter

        addTodoButton = findViewById(R.id.addTodoButton)
        titleEditText = findViewById(R.id.titleEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)

        addTodoButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val newTodo = TodoDataClass(title, description)
            todos.add(newTodo)
            todoAdapter.notifyDataSetChanged()
            clearInputFields()
        }

        addTodoButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val newTodo = TodoDataClass(title, description)
            todos.add(newTodo)
            todoAdapter.notifyDataSetChanged()
            clearInputFields()

            // Hide the keyboard
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(addTodoButton.windowToken, 0)
        }
    }

    private fun clearInputFields() {
        titleEditText.text.clear()
        descriptionEditText.text.clear()
    }
}
data class TodoDataClass(val title: String, val description: String)
