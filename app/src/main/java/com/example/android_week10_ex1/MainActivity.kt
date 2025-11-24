package com.example.android_week10_ex1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val students = mutableListOf<StudentModel>()
    private lateinit var adapter: StudentAdapter
    private lateinit var edtName: EditText
    private lateinit var edtId: EditText
    private var selectedPosition = -1

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
            students,
            onDelete = { position ->
                students.removeAt(position)
                adapter.notifyItemRemoved(position)
                clearInput()
            },
            onSelect = { student ->
                edtId.setText(student.id)
                edtName.setText(student.name)
                selectedPosition = students.indexOf(student)
            }
        )

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val id = edtId.text.toString()
            val name = edtName.text.toString()
            if (id.isNotEmpty() && name.isNotEmpty()) {
                students.add(StudentModel(id, name))
                adapter.notifyItemInserted(students.size - 1)
                clearInput()
            }
        }

        btnUpdate.setOnClickListener {
            if (selectedPosition >= 0) {
                students[selectedPosition].name = edtName.text.toString()
                adapter.notifyItemChanged(selectedPosition)
                clearInput()
                selectedPosition = -1
            }
        }
    }

    private fun clearInput() {
        edtId.text.clear()
        edtName.text.clear()
        selectedPosition = -1
    }
}