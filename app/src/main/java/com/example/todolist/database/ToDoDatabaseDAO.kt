package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDatabaseDAO {

    @Insert
    suspend fun insert(toDo: ToDo)

    @Update
    suspend fun update(toDo: ToDo)

    @Delete
    suspend fun delete(toDo: ToDo)

    @Query("SELECT * from ${ToDo.TABLE_NAME} WHERE todo = :key")
    suspend fun get(key: Long): ToDo?

    @Query("DELETE FROM ${ToDo.TABLE_NAME}")
    suspend fun clear()

    @Query("SELECT * FROM ${ToDo.TABLE_NAME}")
    fun getAllToDos(): LiveData<List<ToDo>>
}
