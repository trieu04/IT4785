package com.example.a10

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnStudent).setOnClickListener {
            startActivity(Intent(this, StudentActivity::class.java))
        }

        findViewById<Button>(R.id.btnGmail).setOnClickListener {
            startActivity(Intent(this, GmailActivity::class.java))
        }

        findViewById<Button>(R.id.btnPlayStore).setOnClickListener {
            startActivity(Intent(this, PlayStoreActivity::class.java))
        }
    }
}