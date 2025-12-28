package com.example.a09

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        findViewById<Button>(R.id.btnCalculator).setOnClickListener {
            startActivity(Intent(this, ConvertActivity::class.java))
        }
        
        findViewById<Button>(R.id.btnInfo).setOnClickListener {
            startActivity(Intent(this, IntegerActivity::class.java))
        }
    }
}