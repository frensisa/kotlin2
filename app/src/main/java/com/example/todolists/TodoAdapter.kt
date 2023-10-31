package com.example.todolists

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TodoAdapter(private val todos: MutableList<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(), ItemTouchHelperAdapter {

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

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }



    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {

            // Option 1: Kotlin synthetic usage. Not recommended and deprecated approach.
            //tvTodoTitle.text = "bla bla text"

            // Option 2: FindViewById
            //val tvTodo:TextView = findViewById(R.id.tvTodoTitle)
            //tvTodo.text = "bla bla text"

            val tvTodoTitle = findViewById<TextView>(R.id.tvTodoTitle)
            val cbDone = findViewById<CheckBox>(R.id.cbDone)
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked

            // Option 2.1: (without new variable declaring if you need to use it only once)
            //findViewById<TextView>(R.id.tvTodoTitle).text = "text"

            // Option 2.2: (without new variable declaring if you need to access different properties of same view)
//            findViewById<TextView>(R.id.tvTodoTitle).apply {
//                text = "bla bla text"
//                visibility = View.VISIBLE
//                width = 0
//                // .... etc.
//            }

            // Option 3. View Binding usage. It's most modern and recp,emded but may be too complex for you now

            }
        }

    }



    override fun getItemCount(): Int {
        return todos.size

    }



    interface ItemTouchHelperAdapter {
        fun onItemMove(fromPosition: Int, toPosition: Int)
        fun onItemDismiss(position: Int)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        // No action needed, as you mentioned you don't want to support item movement.
    }

    override fun onItemDismiss(position: Int) {
        todos.removeAt(position)
        notifyItemRemoved(position)
    }
}