package com.example.todolist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ToDo.TABLE_NAME)
data class ToDo(
    @ColumnInfo(name = "todo") val todo: String,
    @ColumnInfo(name = "is_checked") var isChecked: Boolean,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null
) {
    companion object {
        const val TABLE_NAME = "to_do"
    }
}
