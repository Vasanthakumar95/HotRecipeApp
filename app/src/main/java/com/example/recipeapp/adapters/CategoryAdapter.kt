package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.model.category.Category
import kotlinx.android.synthetic.main.category_cv_item.view.*

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    /**
     * diffutil is used to compare the list of old items and new items and only update the new ones.
     * and this does not block the main thread as it runs in the background
     */

    private val differCallback = object : DiffUtil.ItemCallback<Category>()
    {
        override fun areItemsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.category_cv_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(category.strCategoryThumb).into(img_dish)
            tv_dish_category.text = category.strCategory

            setOnClickListener {
                onItemClickListener?.let { it(category) }
            }

        }
    }

    private var onItemClickListener: ((Category) -> Unit)? = null

    fun setOnItemClickListener(listener: (Category) -> Unit) {
        onItemClickListener = listener
    }
}