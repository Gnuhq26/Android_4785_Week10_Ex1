package com.example.android_week10_ex1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val viewModel: StudentViewModel by viewModels()
    private lateinit var adapter: StudentAdapter
    private lateinit var edtName: EditText
    private lateinit var edtId: EditText
    private var selectedStudent: StudentModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtId = findViewById(R.id.edt_id)
        edtName = findViewById(R.id.edt_name)
        val btnAdd = findViewById<Button>(R.id.btn_add)
        val btnUpdate = findViewById<Button>(R.id.btn_update)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        adapter = StudentAdapter(
            students = emptyList(),
            onDelete = { student ->
                viewModel.deleteStudent(student)
                clearInput()
            },
            onSelect = { student ->
                edtId.setText(student.id)
                edtName.setText(student.name)
                selectedStudent = student
            }
        )

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe students from database
        viewModel.students.observe(this) { students ->
            adapter.updateList(students ?: emptyList())
        }

        btnAdd.setOnClickListener {
            val id = edtId.text.toString()
            val name = edtName.text.toString()
            if (id.isNotEmpty() && name.isNotEmpty()) {
                viewModel.addStudent(StudentModel(id, name))
                clearInput()
            }
        }

        btnUpdate.setOnClickListener {
            val id = edtId.text.toString()
            val name = edtName.text.toString()
            if (selectedStudent != null && id.isNotEmpty() && name.isNotEmpty()) {
                viewModel.updateStudent(StudentModel(id, name))
                clearInput()
            }
        }
    }

    private fun clearInput() {
        edtId.text.clear()
        edtName.text.clear()
        selectedStudent = null
    }
}

