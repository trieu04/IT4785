package com.example.hoc

import android.app.Activity
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*

class InfoActivity : Activity() {
    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var etBirthday: EditText
    private lateinit var btnSelectDate: Button
    private lateinit var calendarView: CalendarView
    private lateinit var etAddress: EditText
    private lateinit var etEmail: EditText
    private lateinit var cbTerms: CheckBox
    private lateinit var btnRegister: Button
    
    private var isCalendarVisible = false
    private var selectedDate = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)
        
        // Initialize views
        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        rbMale = findViewById(R.id.rbMale)
        rbFemale = findViewById(R.id.rbFemale)
        etBirthday = findViewById(R.id.etBirthday)
        btnSelectDate = findViewById(R.id.btnSelectDate)
        calendarView = findViewById(R.id.calendarView)
        etAddress = findViewById(R.id.etAddress)
        etEmail = findViewById(R.id.etEmail)
        cbTerms = findViewById(R.id.cbTerms)
        btnRegister = findViewById(R.id.btnRegister)
        
        // Set calendar initially hidden
        calendarView.visibility = View.GONE
        
        // Setup date picker
        btnSelectDate.setOnClickListener {
            toggleCalendar()
        }
        
        // Calendar date selection
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
            etBirthday.setText(selectedDate)
            toggleCalendar()
        }
        
        // Register button click
        btnRegister.setOnClickListener {
            validateAndRegister()
        }
    }
    
    private fun toggleCalendar() {
        isCalendarVisible = !isCalendarVisible
        calendarView.visibility = if (isCalendarVisible) View.VISIBLE else View.GONE
    }
    
    private fun validateAndRegister() {
        var isValid = true
        
        // Reset all backgrounds to default
        val defaultColor = Color.parseColor("#E0E0E0")
        val errorColor = Color.RED
        
        etFirstName.setBackgroundColor(defaultColor)
        etLastName.setBackgroundColor(defaultColor)
        etBirthday.setBackgroundColor(defaultColor)
        etAddress.setBackgroundColor(defaultColor)
        etEmail.setBackgroundColor(defaultColor)
        
        // Validate First Name
        if (etFirstName.text.toString().trim().isEmpty()) {
            etFirstName.setBackgroundColor(errorColor)
            isValid = false
        }
        
        // Validate Last Name
        if (etLastName.text.toString().trim().isEmpty()) {
            etLastName.setBackgroundColor(errorColor)
            isValid = false
        }
        
        // Validate Gender
        if (!rbMale.isChecked && !rbFemale.isChecked) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show()
            isValid = false
        }
        
        // Validate Birthday
        if (etBirthday.text.toString().trim().isEmpty()) {
            etBirthday.setBackgroundColor(errorColor)
            isValid = false
        }
        
        // Validate Address
        if (etAddress.text.toString().trim().isEmpty()) {
            etAddress.setBackgroundColor(errorColor)
            isValid = false
        }
        
        // Validate Email
        if (etEmail.text.toString().trim().isEmpty()) {
            etEmail.setBackgroundColor(errorColor)
            isValid = false
        }
        
        // Validate Terms checkbox
        if (!cbTerms.isChecked) {
            Toast.makeText(this, "Please agree to Terms of Use", Toast.LENGTH_SHORT).show()
            isValid = false
        }
        
        if (isValid) {
            Toast.makeText(this, "OK!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "NOT OK", Toast.LENGTH_SHORT).show()
        }
    }
}
