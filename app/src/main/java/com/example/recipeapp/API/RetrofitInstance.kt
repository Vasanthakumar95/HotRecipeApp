package com.example.recipeapp.API

import com.example.recipeapp.model.category.categoryResponse
import com.example.recipeapp.model.recipe_by_category.recipeResponse
import com.example.recipeapp.model.recipe_details.details_response
import com.example.recipeapp.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RetrofitInstance {

    @GET("categories.php")
    suspend fun getCategory(): Response<categoryResponse>

    @GET("filter.php")
    suspend fun filterByCategory(
        @Query("c") c: String
    ):Response<recipeResponse>

    @GET("lookup.php")
    suspend fun recipeDetail(
        @Query("i") i: Int
    ):Response<details_response>

    companion object
    {
        fun create(): RetrofitInstance {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient
                .Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(RetrofitInstance::class.java)
        }
    }
}