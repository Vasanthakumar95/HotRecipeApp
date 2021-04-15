package com.example.recipeapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.adapters.CategoryAdapter
import com.example.recipeapp.adapters.RecipeByCategoryAdapter
import com.example.recipeapp.ui.MainActivity
import com.example.recipeapp.ui.viewModel.HomeFragmentViewModel
import com.example.recipeapp.util.Resource
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var homeviewModel: HomeFragmentViewModel
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var recipeByCategoryAdapter: RecipeByCategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeviewModel = (activity as MainActivity).homeFragmentViewModel
        setupRecyclerView()

        categoryAdapter.setOnItemClickListener { category ->
            homeviewModel.getRecipeByCategory(category.strCategory)
        }

        homeviewModel.categoryLiveData.observe(viewLifecycleOwner, Observer { response ->
            when(response)
            {
                is Resource.Success->
                {
                    response.data?.let {
                        categoryAdapter.differ.submitList(it.categories)
                    }
                }
            }

        })

        recipeByCategoryAdapter.setOnItemClickListener { recipe ->
            homeviewModel.getSelectedRecipeDetails(recipe.idMeal.toInt())
            findNavController().navigate(R.id.action_homeFragment_to_recipeDetailsFragment)
        }

        homeviewModel.recipeByCategory.observe(viewLifecycleOwner, Observer { response ->
            when(response)
            {
                is Resource.Success->
                {
                    response.data?.let {
                        recipeByCategoryAdapter.differ.submitList(it.meals)
                    }
                }
            }
        })

    }


    private fun setupRecyclerView()
    {
        categoryAdapter = CategoryAdapter()
        rv_main_category.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
//            addOnScrollListener(this.scrollListener)
        }

        recipeByCategoryAdapter = RecipeByCategoryAdapter()
        rv_category_recipe.apply {
            adapter = recipeByCategoryAdapter
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        }

    }
}