package com.example.todolist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.todoView.ToDoAdapter
import com.example.todolist.todoView.ToDoViewModel
import com.example.todolist.todoView.ToDoViewModelFactory
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val viewModelFactory = ToDoViewModelFactory(application)

        ViewModelProvider(
            this, viewModelFactory
        )[ToDoViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this), R.layout.activity_main, null, false
        )

        setContentView(binding.root)

        initView(binding)

    }

    private fun initView(binding: ActivityMainBinding) {

        val adapter = ToDoAdapter(viewModel::update, viewModel::delete)

        binding.addButton.setOnClickListener {
            val text = binding.editText.text.toString()
            if (text.isEmpty()) {
                return@setOnClickListener
            }
            viewModel.insert(text)
            binding.editText.text.clear()
        }

        binding.editText.setOnEditorActionListener { _, _, _ ->
            binding.addButton.performClick()
        }

        viewModel.todosLiveData.observe(this) { todos ->
            adapter.todos = todos
        }

        binding.rv.adapter = adapter
    }

}
