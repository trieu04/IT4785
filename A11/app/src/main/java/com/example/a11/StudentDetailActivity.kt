package com.example.a11

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class StudentDetailActivity : Activity() {

    private lateinit var etMssv: EditText
    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etAddress: EditText
    private lateinit var btnUpdate: Button
    private lateinit var btnCancel: Button
    
    private var student: Student? = null
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        etMssv = findViewById(R.id.etMssv)
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        etAddress = findViewById(R.id.etAddress)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnCancel = findViewById(R.id.btnCancel)

        // Get student data from intent
        student = intent.getParcelableExtra("student")
        position = intent.getIntExtra("position", -1)

        // Display student information
        student?.let {
            etMssv.setText(it.mssv)
            etName.setText(it.name)
            etPhone.setText(it.phone)
            etAddress.setText(it.address)
        }

        btnUpdate.setOnClickListener {
            updateStudent()
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun updateStudent() {
        val mssv = etMssv.text.toString().trim()
        val name = etName.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val address = etAddress.text.toString().trim()

        if (mssv.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        val updatedStudent = Student(mssv, name, phone, address)
        val resultIntent = Intent()
        resultIntent.putExtra("student", updatedStudent)
        resultIntent.putExtra("position", position)
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}
