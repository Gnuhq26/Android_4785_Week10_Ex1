package com.example.android_week10_ex1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    private val _students = MutableLiveData<MutableList<StudentModel>>(mutableListOf())
    val students: LiveData<MutableList<StudentModel>> get() = _students

    fun addStudent(student: StudentModel) {
        _students.value?.add(student)
        _students.value = _students.value // Trigger LiveData update
    }

    fun updateStudent(position: Int, student: StudentModel) {
        _students.value?.set(position, student)
        _students.value = _students.value
    }

    fun deleteStudent(position: Int) {
        _students.value?.removeAt(position)
        _students.value = _students.value
    }
}
