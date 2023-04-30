package com.example.todolist.todoView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.database.ToDo
import com.example.todolist.databinding.ListItemBinding

class ToDoAdapter(
    private val onUpdate: (todo: ToDo) -> Unit, private val onDelete: (toDo: ToDo) -> Unit
) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    var todos: List<ToDo> = emptyList()
        @SuppressLint("NotifyDataSetChanged") set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoAdapter.ViewHolder, position: Int) {
        val todo = todos[position]
        holder.bind(todo)

    }

    override fun getItemCount() = todos.size

    inner class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: ToDo) {
            binding.checkbox.apply {
                text = todo.todo
                setOnCheckedChangeListener(null)
                isChecked = todo.isChecked
                setOnCheckedChangeListener { _, isChecked ->
                    onUpdate.invoke(todo.copy(isChecked = isChecked))
                }
            }
            binding.delete.setOnClickListener {
                onDelete.invoke(todo)
            }
        }
    }
}
