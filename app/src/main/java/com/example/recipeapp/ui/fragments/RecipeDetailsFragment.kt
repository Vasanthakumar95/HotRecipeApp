package com.example.recipeapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.ui.MainActivity
import com.example.recipeapp.ui.viewModel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.category_cv_item.view.*
import kotlinx.android.synthetic.main.fragment_recipe_details.*

class RecipeDetailsFragment : Fragment(R.layout.fragment_recipe_details) {

    private lateinit var homeviewModel: HomeFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeviewModel = (activity as MainActivity).homeFragmentViewModel

        homeviewModel.selectedRecipeLiveData.observe(viewLifecycleOwner, Observer {
//            it.data?.meals?.get(0)?

            load_img(it.data?.meals?.get(0)?.strMealThumb.toString())
            tv_recipe_title.text = it.data?.meals?.get(0)?.strMeal
            tv_meal_type.text = "Meal Type: "+ it.data?.meals?.get(0)?.strTags
            tv_meal_origin.text = "Meal Origin: " + it.data?.meals?.get(0)?.strArea

            var ingredients = "${it.data?.meals?.get(0)?.strIngredient1}  ${it.data?.meals?.get(0)?.strMeasure1}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient2}  ${it.data?.meals?.get(0)?.strMeasure2}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient3}  ${it.data?.meals?.get(0)?.strMeasure3}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient4}  ${it.data?.meals?.get(0)?.strMeasure4}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient5}  ${it.data?.meals?.get(0)?.strMeasure5}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient6}  ${it.data?.meals?.get(0)?.strMeasure6}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient7}  ${it.data?.meals?.get(0)?.strMeasure7}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient8}  ${it.data?.meals?.get(0)?.strMeasure8}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient9}  ${it.data?.meals?.get(0)?.strMeasure9}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient10}  ${it.data?.meals?.get(0)?.strMeasure10}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient11}  ${it.data?.meals?.get(0)?.strMeasure11}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient12}  ${it.data?.meals?.get(0)?.strMeasure12}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient13}  ${it.data?.meals?.get(0)?.strMeasure13}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient14}  ${it.data?.meals?.get(0)?.strMeasure14}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient15}  ${it.data?.meals?.get(0)?.strMeasure15}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient16}  ${it.data?.meals?.get(0)?.strMeasure16}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient17}  ${it.data?.meals?.get(0)?.strMeasure17}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient18}  ${it.data?.meals?.get(0)?.strMeasure18}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient19}  ${it.data?.meals?.get(0)?.strMeasure19}\n"+
                            "${it.data?.meals?.get(0)?.strIngredient20}  ${it.data?.meals?.get(0)?.strMeasure20}\n"

            tv_ingredients.text = ingredients


            tv_instruction.text = it.data?.meals?.get(0)?.strInstructions
        })

        ib_back.setOnClickListener {
            findNavController().navigate(R.id.action_recipeDetailsFragment_to_homeFragment)
        }

        ib_yt.setOnClickListener {
            findNavController().navigate(R.id.action_recipeDetailsFragment_to_sourceFragment)
        }

    }

    fun load_img(url: String){
        Glide.with(this).load(url)
            .into(iv_recipe_thumb)
    }

}