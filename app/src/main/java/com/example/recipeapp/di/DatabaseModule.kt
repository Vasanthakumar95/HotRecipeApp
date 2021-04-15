package com.example.recipeapp.di

import android.content.Context
import com.example.recipeapp.db.CategoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideCategoryDatabase(@ApplicationContext context: Context): CategoryDatabase
    {
        return CategoryDatabase.getInstance(context)
    }

}