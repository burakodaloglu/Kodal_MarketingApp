package com.burakkodaloglu.benimprojem.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.burakkodaloglu.benimprojem.databinding.ImageContainerBinding

class ImageAdapter(private val imageList: ArrayList<Int>, private val viewPager2: ViewPager2) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) =
        holder.bind(imageList[position], imageList.size, position, viewPager2, runnable)

    class ImageViewHolder(private val binding: ImageContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Int,
            imageListSize: Int,
            position: Int,
            viewPager2: ViewPager2,
            runnable: Runnable
        ) {
            binding.imageView.setImageResource(item)
            if (position == imageListSize - 1)
                viewPager2.post(runnable)
        }
    }

    override fun getItemCount() = imageList.size

    private val runnable = Runnable {
        imageList.addAll(imageList)
        notifyDataSetChanged()
    }
}