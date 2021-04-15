package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.model.recipe_by_category.Meal
import kotlinx.android.synthetic.main.recipe_cv_item.view.*

class RecipeByCategoryAdapter: RecyclerView.Adapter<RecipeByCategoryAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    private val differCallback = object : DiffUtil.ItemCallback<Meal>()
    {
        override fun areItemsTheSame(
            oldItem: Meal,
            newItem: Meal
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Meal,
            newItem: Meal
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recipe_cv_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(recipe.strMealThumb).into(img_dish)
            tv_recipe_name.text = recipe.strMeal

            setOnClickListener {
                onItemClickListener?.let { it(recipe) }
            }
        }
    }

    private var onItemClickListener: ((Meal) -> Unit)? = null

    fun setOnItemClickListener(listener: (Meal) -> Unit) {
        onItemClickListener = listener
    }

}