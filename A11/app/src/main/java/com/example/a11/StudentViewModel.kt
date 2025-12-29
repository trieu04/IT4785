package com.example.a11

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    private val _students = MutableLiveData<MutableList<Student>>(mutableListOf())
    val students: LiveData<MutableList<Student>> = _students

    init {
        // Initial data
        _students.value?.apply {
            add(Student("20200001", "Nguyen Van A", "0123456789", "Ha Noi"))
            add(Student("20200002", "Tran Thi B", "0987654321", "Ho Chi Minh"))
            add(Student("20200003", "Le Van C", "0912345678", "Da Nang"))
        }
    }

    fun addStudent(student: Student) {
        val currentList = _students.value ?: mutableListOf()
        currentList.add(student)
        _students.value = currentList
    }

    fun updateStudent(index: Int, student: Student) {
        val currentList = _students.value ?: return
        if (index in 0 until currentList.size) {
            currentList[index] = student
            _students.value = currentList
        }
    }

    fun removeStudent(index: Int) {
        val currentList = _students.value ?: return
        if (index in 0 until currentList.size) {
            currentList.removeAt(index)
            _students.value = currentList
        }
    }
}
