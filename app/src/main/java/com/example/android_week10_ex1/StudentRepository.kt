package com.example.android_week10_ex1

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StudentRepository(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("StudentDB", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val _allStudents = MutableLiveData<List<StudentModel>>()
    val allStudents: LiveData<List<StudentModel>> = _allStudents

    init {
        loadStudents()
    }

    private fun loadStudents() {
        val json = sharedPreferences.getString("students", "[]")
        val type = object : TypeToken<MutableList<StudentModel>>() {}.type
        val students: MutableList<StudentModel> = gson.fromJson(json, type) ?: mutableListOf()
        _allStudents.value = students
    }

    private fun saveStudents(students: List<StudentModel>) {
        val json = gson.toJson(students)
        sharedPreferences.edit().putString("students", json).apply()
        _allStudents.value = students
    }

    fun insert(student: StudentModel) {
        val students = _allStudents.value?.toMutableList() ?: mutableListOf()
        students.add(student)
        saveStudents(students)
    }

    fun update(student: StudentModel) {
        val students = _allStudents.value?.toMutableList() ?: mutableListOf()
        val index = students.indexOfFirst { it.id == student.id }
        if (index >= 0) {
            students[index] = student
            saveStudents(students)
        }
    }

    fun delete(student: StudentModel) {
        val students = _allStudents.value?.toMutableList() ?: mutableListOf()
        students.removeIf { it.id == student.id }
        saveStudents(students)
    }

    fun deleteAll() {
        saveStudents(emptyList())
    }

    fun getStudentById(studentId: String): StudentModel? {
        return _allStudents.value?.find { it.id == studentId }
    }
}
