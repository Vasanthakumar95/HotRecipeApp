package com.example.recipeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.API.RetrofitInstance
import com.example.recipeapp.R
import com.example.recipeapp.repository.RecipeRepository
import com.example.recipeapp.ui.viewModel.HomeFragmentViewModel
import com.example.recipeapp.ui.viewModelProviderFactory.RecipeViewModelProviderFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.contracts.contract

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    lateinit var homeFragmentViewModel: HomeFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.splashScreen)
        setContentView(R.layout.activity_main)

        val recipeRepository = RecipeRepository(retrofitInstance = RetrofitInstance.create())
        val viewModelProviderFactory = RecipeViewModelProviderFactory(application, recipeRepository)
        homeFragmentViewModel = ViewModelProvider(this, viewModelProviderFactory).get(HomeFragmentViewModel::class.java)

    }
}