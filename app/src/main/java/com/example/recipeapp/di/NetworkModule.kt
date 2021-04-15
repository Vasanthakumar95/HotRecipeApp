package com.example.recipeapp.di

import com.example.recipeapp.API.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkModule(): RetrofitInstance
    {
        return RetrofitInstance.create()
    }
}