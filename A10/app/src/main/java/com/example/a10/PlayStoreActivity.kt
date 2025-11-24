package com.example.a10

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PlayStoreActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_store)

        val rvCategories: RecyclerView = findViewById(R.id.rvCategories)
        rvCategories.layoutManager = LinearLayoutManager(this)

        val categories = listOf(
            CategoryModel("List 1", listOf(
                AppModel("App 1", "4.8 ★", 0),
                AppModel("App 2", "4.8 ★", 0),
                AppModel("App 3", "4.9 ★", 0),
                AppModel("App A", "4.5 ★", 0),
                AppModel("App B", "4.2 ★", 0),
                AppModel("App C", "4.8 ★", 0),
            )),
            CategoryModel("Recommended for you", listOf(
                AppModel("App ABC", "4.6 ★", 0),
                AppModel("App XYZ", "4.9 ★", 0),
                AppModel("App 123", "4.3 ★", 0),
            )),
        )

        rvCategories.adapter = CategoryAdapter(categories)
    }
}
