package com.example.a10

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudentActivity : Activity() {

    private lateinit var etName: EditText
    private lateinit var etMssv: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var rvStudents: RecyclerView
    private lateinit var adapter: StudentAdapter
    private val students = mutableListOf<Student>()
    private var selectedStudent: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        etName = findViewById(R.id.etName)
        etMssv = findViewById(R.id.etMssv)
        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        rvStudents = findViewById(R.id.rvStudents)

        // Initialize sample data
        students.add(Student("Nguyen Van A", "20200001"))
        students.add(Student("Tran Thi B", "20200002"))
        students.add(Student("Le Van C", "20200003"))

        adapter = StudentAdapter(students, this::onStudentClick, this::onDeleteClick)
        rvStudents.layoutManager = LinearLayoutManager(this)
        rvStudents.adapter = adapter

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val mssv = etMssv.text.toString()
            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                students.add(Student(name, mssv))
                adapter.notifyItemInserted(students.size - 1)
                clearInput()
            } else {
                Toast.makeText(this, "Please enter Name and MSSV", Toast.LENGTH_SHORT).show()
            }
        }

        btnUpdate.setOnClickListener {
            selectedStudent?.let { student ->
                val name = etName.text.toString()
                val mssv = etMssv.text.toString()
                if (name.isNotEmpty() && mssv.isNotEmpty()) {
                    student.name = name
                    student.mssv = mssv // Allow updating MSSV as well, or just name as per requirement? Req says "update name", but usually both. Let's update both.
                    adapter.notifyDataSetChanged()
                    clearInput()
                    selectedStudent = null
                } else {
                    Toast.makeText(this, "Please enter Name and MSSV", Toast.LENGTH_SHORT).show()
                }
            } ?: run {
                Toast.makeText(this, "Please select a student first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onStudentClick(student: Student) {
        selectedStudent = student
        etName.setText(student.name)
        etMssv.setText(student.mssv)
    }

    private fun onDeleteClick(student: Student) {
        val position = students.indexOf(student)
        if (position != -1) {
            students.removeAt(position)
            adapter.notifyItemRemoved(position)
            if (selectedStudent == student) {
                clearInput()
                selectedStudent = null
            }
        }
    }

    private fun clearInput() {
        etName.text.clear()
        etMssv.text.clear()
        etName.requestFocus()
    }
}
