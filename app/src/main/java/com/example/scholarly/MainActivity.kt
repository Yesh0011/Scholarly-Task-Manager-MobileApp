 package com.example.scholarly

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.scholarly.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  lateinit var db:TaskDataBaseHelper
    private lateinit var taskAdapter: TasksAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        db = TaskDataBaseHelper(this)
        taskAdapter = TasksAdapter(db.getAllTasks(),this)

        binding.tasksRecycleView.layoutManager = LinearLayoutManager(this)
        binding.tasksRecycleView.adapter = taskAdapter

        binding.addButton.setOnClickListener{
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }


    }

     override fun onResume() {
         super.onResume()
         taskAdapter.refreshData(db.getAllTasks())
     }
}