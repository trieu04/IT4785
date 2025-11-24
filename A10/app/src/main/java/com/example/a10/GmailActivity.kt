package com.example.a10

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GmailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gmail)

        val rvEmails: RecyclerView = findViewById(R.id.rvEmails)
        rvEmails.layoutManager = LinearLayoutManager(this)

        val emails = listOf(
            EmailModel("Nguyễn Văn A", "Thông báo họp", "Họp ABC XYZ", "12:34", Color.parseColor("#4285F4")),
            EmailModel("Nguyễn Văn B", "Báo cáo dự án tháng 11", "Báo cáo tiến độ dự án...", "11:22", Color.parseColor("#E91E63")),
            EmailModel("Fayedark", "Welcome to Fayedark", "www.fayedark.com", "11:04", Color.parseColor("#4CAF50")),
            EmailModel("Admin", "Cập nhật hệ thống", "Hệ thống sẽ cập nhật", "10:26", Color.parseColor("#607D8B")),
            EmailModel("Nguyễn Văn A", "Thông báo họp", "Họp ABC XYZ", "12:34", Color.parseColor("#4285F4")),
            EmailModel("Nguyễn Văn B", "Báo cáo dự án tháng 11", "Báo cáo tiến độ dự án...", "11:22", Color.parseColor("#E91E63")),
            EmailModel("Fayedark", "Welcome to Fayedark", "www.fayedark.com", "11:04", Color.parseColor("#4CAF50")),
            EmailModel("Admin", "Cập nhật hệ thống", "Hệ thống sẽ cập nhật", "10:26", Color.parseColor("#607D8B")),
            EmailModel("Nguyễn Văn A", "Thông báo họp", "Họp ABC XYZ", "12:34", Color.parseColor("#4285F4")),
            EmailModel("Nguyễn Văn B", "Báo cáo dự án tháng 11", "Báo cáo tiến độ dự án...", "11:22", Color.parseColor("#E91E63")),
            EmailModel("Fayedark", "Welcome to Fayedark", "www.fayedark.com", "11:04", Color.parseColor("#4CAF50")),
            EmailModel("Admin", "Cập nhật hệ thống", "Hệ thống sẽ cập nhật", "10:26", Color.parseColor("#607D8B")),
            EmailModel("Nguyễn Văn A", "Thông báo họp", "Họp ABC XYZ", "12:34", Color.parseColor("#4285F4")),
            EmailModel("Nguyễn Văn B", "Báo cáo dự án tháng 11", "Báo cáo tiến độ dự án...", "11:22", Color.parseColor("#E91E63")),
            EmailModel("Fayedark", "Welcome to Fayedark", "www.fayedark.com", "11:04", Color.parseColor("#4CAF50")),
            EmailModel("Admin", "Cập nhật hệ thống", "Hệ thống sẽ cập nhật", "10:26", Color.parseColor("#607D8B")),
            )

        rvEmails.adapter = EmailAdapter(emails)
    }
}
