package com.burakkodaloglu.benimprojem.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.burakkodaloglu.benimprojem.data.model.Category
import com.burakkodaloglu.benimprojem.databinding.ItemCategoryBinding

class CategoryAdapter(private val categoryList: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ItemCategoryViewHolder>() {

    var onCategoryClick: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCategoryViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemCategoryViewHolder, position: Int) =
        holder.bind(categoryList[position])

    inner class ItemCategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Category) {
            binding.icons.setImageResource(item.image)
            binding.name.text = item.name

            binding.root.setOnClickListener {
                onCategoryClick(item.name)
            }
        }
    }

    override fun getItemCount() = categoryList.size
}