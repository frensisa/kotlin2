package com.example.todolists

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvTodoItems = findViewById<RecyclerView>(R.id.TodoItems)
        todoAdapter = TodoAdapter(mutableListOf())
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        // Attach ItemTouchHelper for swipe-to-delete
        val itemTouchHelper = ItemTouchHelper(TodoItemTouchHelperCallback(todoAdapter))
        itemTouchHelper.attachToRecyclerView(rvTodoItems)

        // Add todo button logic
        val btnAddTodo = findViewById<Button>(R.id.btnAddToDo)
        btnAddTodo.setOnClickListener {
            val etTodoTitle = findViewById<EditText>(R.id.etToDoTitle)
            val todoTitle = etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }
    }
}