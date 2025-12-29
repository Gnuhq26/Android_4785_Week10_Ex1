package com.example.android_week10_ex1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private var students: List<StudentModel>,
    val onDelete: (StudentModel) -> Unit,
    val onSelect: (StudentModel) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(itemView, onDelete, onSelect)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)
    }

    override fun getItemCount() = students.size

    fun updateList(newStudents: List<StudentModel>) {
        students = newStudents
        notifyDataSetChanged()
    }

    class StudentViewHolder(
        itemView: View,
        val onDelete: (StudentModel) -> Unit,
        val onSelect: (StudentModel) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.text_name)
        val idView: TextView = itemView.findViewById(R.id.text_id)
        val deleteBtn: ImageView = itemView.findViewById(R.id.btn_delete)

        private var currentStudent: StudentModel? = null

        init {
            deleteBtn.setOnClickListener {
                currentStudent?.let { onDelete(it) }
            }
            itemView.setOnClickListener {
                currentStudent?.let { onSelect(it) }
            }
        }

        fun bind(student: StudentModel) {
            currentStudent = student
            nameView.text = student.name
            idView.text = student.id
        }
    }
}
