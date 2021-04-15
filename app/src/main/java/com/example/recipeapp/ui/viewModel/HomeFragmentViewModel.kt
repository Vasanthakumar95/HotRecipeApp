package com.example.recipeapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.model.category.categoryResponse
import com.example.recipeapp.model.recipe_by_category.recipeResponse
import com.example.recipeapp.model.recipe_details.details_response
import com.example.recipeapp.repository.RecipeRepository
import com.example.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
): ViewModel(){

    val categoryLiveData: MutableLiveData<Resource<categoryResponse>> = MutableLiveData()
    val recipeByCategory: MutableLiveData<Resource<recipeResponse>> = MutableLiveData()
    val selectedRecipeLiveData: MutableLiveData<Resource<details_response>> = MutableLiveData()

    init {
        //retrieve all available category
        getCategory()
        //load by default the first category
        getRecipeByCategory("Beef")
    }

    /**
     * get all available category
     */

    fun getCategory()
    {
        viewModelScope.launch {
            val response = recipeRepository.getAllCategory()
            categoryLiveData.postValue(handleCategoryResponse(response))
        }
    }

    fun handleCategoryResponse(response: Response<categoryResponse>) : Resource<categoryResponse>
    {
        if(response.isSuccessful)
        {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    /**
     * get recipe by selected category
     */

    fun getRecipeByCategory(category: String)
    {
        viewModelScope.launch {
            val response = recipeRepository.filterByCategory(category)
            recipeByCategory.postValue(handleRecipeByCategoryResponse(response))
        }
    }

    fun handleRecipeByCategoryResponse(response: Response<recipeResponse>) : Resource<recipeResponse>
    {
        if(response.isSuccessful)
        {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    /**
     * get selected recipe details
     */

    fun getSelectedRecipeDetails(id: Int)
    {
        viewModelScope.launch {
            val response = recipeRepository.getRecipeDetails(id)
            selectedRecipeLiveData.postValue(handleRecipeDetailsResponse(response))
        }
    }

    fun handleRecipeDetailsResponse(response: Response<details_response>) : Resource<details_response>
    {
        if(response.isSuccessful)
        {
            response.body()?.let{ resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}