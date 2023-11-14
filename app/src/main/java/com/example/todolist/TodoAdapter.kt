package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*
class TodoAdapter(private val todos: MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val todoTitle: TextView = itemView.findViewById(R.id.tvTodoTitle)
        val doneCheckBox: CheckBox = itemView.findViewById(R.id.cbDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        with(holder) {
            todoTitle.text = curTodo.title
            doneCheckBox.isChecked = curTodo.isChecked
            toggleStrikeThrough(todoTitle, curTodo.isChecked)
            doneCheckBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(todoTitle, isChecked)
                curTodo.isChecked = isChecked
            }
        }
    }

    override fun getItemCount(): Int = todos.size

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { it.isChecked }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        tvTodoTitle.paintFlags = if (isChecked) {
            tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}


















