package com.example.a11

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudentListActivity : Activity() {

    private lateinit var rvStudents: RecyclerView
    private lateinit var adapter: StudentAdapter
    private val students = mutableListOf<Student>()

    companion object {
        const val REQUEST_ADD_STUDENT = 1
        const val REQUEST_EDIT_STUDENT = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        rvStudents = findViewById(R.id.rvStudents)

        // Initialize sample data
        students.add(Student("20200001", "Nguyen Van A", "0123456789", "Ha Noi"))
        students.add(Student("20200002", "Tran Thi B", "0987654321", "Ho Chi Minh"))
        students.add(Student("20200003", "Le Van C", "0912345678", "Da Nang"))

        adapter = StudentAdapter(students) { student, position ->
            onStudentClick(student, position)
        }
        rvStudents.layoutManager = LinearLayoutManager(this)
        rvStudents.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_student_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_student -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivityForResult(intent, REQUEST_ADD_STUDENT)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onStudentClick(student: Student, position: Int) {
        val intent = Intent(this, StudentDetailActivity::class.java)
        intent.putExtra("student", student)
        intent.putExtra("position", position)
        startActivityForResult(intent, REQUEST_EDIT_STUDENT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_ADD_STUDENT -> {
                    val newStudent = data.getParcelableExtra<Student>("student")
                    newStudent?.let {
                        students.add(it)
                        adapter.notifyItemInserted(students.size - 1)
                    }
                }
                REQUEST_EDIT_STUDENT -> {
                    val updatedStudent = data.getParcelableExtra<Student>("student")
                    val position = data.getIntExtra("position", -1)
                    if (updatedStudent != null && position != -1) {
                        students[position] = updatedStudent
                        adapter.notifyItemChanged(position)
                    }
                }
            }
        }
    }
}
