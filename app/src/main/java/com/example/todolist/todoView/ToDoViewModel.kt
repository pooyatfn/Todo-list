package com.example.todolist.todoView

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.ToDo
import com.example.todolist.database.ToDoDatabase
import com.example.todolist.database.ToDoDatabaseDAO
import kotlinx.coroutines.launch

class ToDoViewModel(
    application: Application
) : ViewModel() {

    private val todoDao: ToDoDatabaseDAO = ToDoDatabase.getInstance(application).toDoDatabase

    val todosLiveData = todoDao.getAllToDos()

    fun insert(text: String) = viewModelScope.launch {
        val item = ToDo(text, false)
        todoDao.insert(item)
    }

    fun delete(toDo: ToDo) = viewModelScope.launch {
        todoDao.delete(toDo)
    }

    fun update(toDo: ToDo) = viewModelScope.launch {
        todoDao.update(toDo)
    }

    fun clear() = viewModelScope.launch {
        todoDao.clear()
    }

}
