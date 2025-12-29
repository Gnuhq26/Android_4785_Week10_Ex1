package com.example.android_week10_ex1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudentRepository
    val students: LiveData<List<StudentModel>>

    init {
        repository = StudentRepository(application)
        students = repository.allStudents
    }

    fun addStudent(student: StudentModel) {
        repository.insert(student)
    }

    fun updateStudent(student: StudentModel) {
        repository.update(student)
    }

    fun deleteStudent(student: StudentModel) {
        repository.delete(student)
    }

    fun deleteAllStudents() {
        repository.deleteAll()
    }
}
