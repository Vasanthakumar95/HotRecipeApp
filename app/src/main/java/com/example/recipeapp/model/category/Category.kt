package com.example.recipeapp.model.category

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "category"
)
data class Category(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)