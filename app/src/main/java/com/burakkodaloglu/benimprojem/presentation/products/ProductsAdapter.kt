package com.burakkodaloglu.benimprojem.presentation.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.burakkodaloglu.benimprojem.data.model.Category
import com.burakkodaloglu.benimprojem.data.model.Product
import com.burakkodaloglu.benimprojem.databinding.ItemCategoryBinding
import com.burakkodaloglu.benimprojem.databinding.ItemProductBinding
import com.burakkodaloglu.benimprojem.presentation.home.CategoryAdapter

class ProductsAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ItemProductViewHolder>() {

    var onAddCartClick: (Product) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProductViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemProductViewHolder, position: Int) =
        holder.bind(productList[position])

    inner class ItemProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            binding.imgImage.setImageResource(item.image)
            binding.tvName.text = item.name
            binding.tvPrice.text = "${item.price} ₺"

            binding.btnAddCart.setOnClickListener {
                onAddCartClick(item)
            }
        }
    }

    override fun getItemCount() = productList.size
}