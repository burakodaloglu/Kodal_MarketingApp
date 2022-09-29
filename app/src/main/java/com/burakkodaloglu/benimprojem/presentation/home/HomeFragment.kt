package com.burakkodaloglu.benimprojem.presentation.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.burakkodaloglu.benimprojem.R
import com.burakkodaloglu.benimprojem.data.model.Category
import com.burakkodaloglu.benimprojem.databinding.FragmentHomeBinding
import kotlin.math.abs

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 5000)
            }
        })
    }

    //Grid
    private fun initView() {

        val listArray = arrayListOf(
            Category(R.drawable.phone, "Elektronik,Cep Telefonu"),
            Category(R.drawable.koltuk, "Ev, Yaşam, Ofis, Kırtasiye"),
            Category(R.drawable.baby, "Anne, Bebek, Oyyuncak"),
            Category(R.drawable.tshirt, "Moda, Ayakkabı"),
            Category(R.drawable.book, "Kitap, Müzik, Hobi"),
            Category(R.drawable.bisiklet, "Spor, Outdoor"),
            Category(R.drawable.kisiselbakim, "Sağlık, Kozmetik"),
            Category(R.drawable.tamir, "Bahçe, Yapı Market"),
            Category(R.drawable.petshop, "Petshop")
        )

        refreshAdapter(listArray)

        Looper.myLooper()?.let {
            //Looper.myLooper() null değilse
            handler = Handler(it)
        } ?: run {
            // If-Else'teki else kısmı
        }

        val imageList = arrayListOf(
            R.drawable.slider1,
            R.drawable.slider2,
            R.drawable.slider3,
            R.drawable.slider4,
            R.drawable.slider5,
        )

        binding.viewPager.apply {

            val transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(40))
            transformer.addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0f + r + 0f
            }

            adapter = ImageAdapter(imageList, this)

            adapter = adapter
            offscreenPageLimit = 3
            clipToPadding = false
            clipChildren = false
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(transformer)
        }
    }

    private fun refreshAdapter(list: ArrayList<Category>) {

        val categoryAdapter = CategoryAdapter(list)

        categoryAdapter.onCategoryClick = {
            val action = HomeFragmentDirections.homeToProducts(it)
            findNavController().navigate(action)
        }

        binding.rvCategories.adapter = categoryAdapter
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 5000)
    }

    private val runnable = Runnable {
        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
    }
}
