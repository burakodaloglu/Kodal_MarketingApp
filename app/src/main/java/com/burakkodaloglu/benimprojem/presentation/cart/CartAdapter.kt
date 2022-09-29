package com.burakkodaloglu.benimprojem.presentation.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.burakkodaloglu.benimprojem.data.model.Product
import com.burakkodaloglu.benimprojem.databinding.ItemCartBinding

class CartAdapter : RecyclerView.Adapter<CartAdapter.ItemCartProductViewHolder>() {

    private val productList = ArrayList<Product>()

    var onAddClick: (String) -> Unit = {}
    var onDeleteClick: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCartProductViewHolder {
        val binding =
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemCartProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemCartProductViewHolder, position: Int) =
        holder.bind(productList[position])

    inner class ItemCartProductViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {

            with(binding) {
                imgImage.setImageResource(item.image)
                tvName.text = item.name
                tvPrice.text = "${item.price} ₺"
                tvCount.text = item.count.toString()

                imgAdd.setOnClickListener {
                    onAddClick(item.name)
                }

                imgDelete.setOnClickListener {
                    onDeleteClick(item.name)
                }
            }
        }
    }

    override fun getItemCount() = productList.size

    fun updateList(updatedList: ArrayList<Product>) {
        productList.clear()
        productList.addAll(updatedList)
        notifyItemRangeChanged(0, updatedList.size)
    }
}