package com.example.scholarly

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.scholarly.databinding.ActivityAddTaskBinding
import com.example.scholarly.databinding.ActivityUpdateTaskBinding

class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db: TaskDataBaseHelper
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        db = TaskDataBaseHelper(this)

        taskId = intent.getIntExtra("task_id", -1)
        if(taskId == -1){
            finish()
            return
        }

        val task = db.getTaskById(taskId)
        binding.edittitlEdit.setText(task.title)
        binding.editeditContent.setText(task.content)

        binding.editsaveButton.setOnClickListener{
            val newTitle = binding.edittitlEdit.text.toString()
            val newContent = binding.editeditContent.text.toString()
            val updateTask = Task(taskId,newTitle,newContent)
            db.updateTask(updateTask)
            finish()
            Toast.makeText(this,"Changed saved Successfully",Toast.LENGTH_SHORT).show()
        }

    }
}