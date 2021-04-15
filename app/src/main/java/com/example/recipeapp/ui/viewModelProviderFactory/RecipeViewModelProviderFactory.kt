package com.example.recipeapp.ui.viewModelProviderFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.repository.RecipeRepository
import com.example.recipeapp.ui.viewModel.HomeFragmentViewModel
import javax.inject.Inject

class RecipeViewModelProviderFactory @Inject constructor(
    val app: Application,
    val recipeRepository: RecipeRepository
) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeFragmentViewModel(recipeRepository) as T
        }
}