package com.example.todolists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(private val todos: MutableList<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

   class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {

            // Option 1: Kotlin synthetic usage. Not recomended and depricated approach.
            tvTodoTitle.text = "bla bla text"

            // Option 2: FindViewById
            val tvTodo:TextView = findViewById(R.id.tvTodoTitle)
            tvTodo.text = "bla bla text"

            // Option 2.1: (without new variable declaring if you need to use it only once)
            findViewById<TextView>(R.id.tvTodoTitle).text = "bla bla text"

            // Option 2.2: (without new variable declaring if you need to access different properties of same view)
            findViewById<TextView>(R.id.tvTodoTitle).apply {
                text = "bla bla text"
                visibility = View.VISIBLE
                width = 0
                // .... etc.
            }

            // Option 3. View Binding usage. It's most modern and recp,emded but may be too complex for you now

        }
    }

    override fun getItemCount(): Int {
        return todos.size

    }
}