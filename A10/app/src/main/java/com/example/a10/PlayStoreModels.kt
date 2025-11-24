package com.example.a10

data class AppModel(
    val name: String,
    val rating: String,
    val imageRes: Int
)

data class CategoryModel(
    val title: String,
    val apps: List<AppModel>
)
