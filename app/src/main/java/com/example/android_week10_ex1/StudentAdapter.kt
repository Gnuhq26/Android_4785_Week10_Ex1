package com.example.android_week10_ex1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    val students: List<StudentModel>,
    val onDelete: (Int) -> Unit,
    val onSelect: (StudentModel) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        // Pass the students list to the ViewHolder
        return StudentViewHolder(itemView, students, onDelete, onSelect)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.nameView.text = student.name
        holder.idView.text = student.id
        // No need to set position here anymore
    }

    override fun getItemCount() = students.size

    class StudentViewHolder(
        itemView: View,
        // Add students list to the constructor
        val students: List<StudentModel>,
        val onDelete: (Int) -> Unit,
        val onSelect: (StudentModel) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.text_name)
        val idView: TextView = itemView.findViewById(R.id.text_id)
        val deleteBtn: ImageView = itemView.findViewById(R.id.btn_delete)
        // REMOVED: var position = 0

        init {
            deleteBtn.setOnClickListener {
                // Use adapterPosition to get the current position
                val currentPosition = adapterPosition
                if (currentPosition != RecyclerView.NO_POSITION) {
                    onDelete(currentPosition)
                }
            }
            itemView.setOnClickListener {
                // Use adapterPosition to get the current position and the corresponding student
                val currentPosition = adapterPosition
                if (currentPosition != RecyclerView.NO_POSITION) {
                    onSelect(students[currentPosition])
                }
            }
        }
    }
}
