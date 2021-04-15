package com.example.recipeapp.repository

import com.example.recipeapp.API.RetrofitInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    private val retrofitInstance: RetrofitInstance) {

    suspend fun getAllCategory() = retrofitInstance.getCategory()

    suspend fun filterByCategory(category: String) = retrofitInstance.filterByCategory(category)

    suspend fun getRecipeDetails(id: Int) = retrofitInstance.recipeDetail(id)

}