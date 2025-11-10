package com.example.hoc

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
            startActivity(Intent(this, CalcActivity::class.java))
        }
        
        findViewById<Button>(R.id.btnInfo).setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }

        val listView = findViewById<ListView>(R.id.listView);
        listView.adapter = ArrayAdapter(
            this,
            R.layout.list_item,
            listOf("123", "123"),
        );
        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }

        }
    }
}