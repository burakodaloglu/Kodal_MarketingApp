package com.burakkodaloglu.benimprojem.presentation.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.burakkodaloglu.benimprojem.common.ProductsData
import com.burakkodaloglu.benimprojem.data.model.Product
import com.burakkodaloglu.benimprojem.databinding.FragmentProductsBinding
import com.burakkodaloglu.benimprojem.presentation.cart.CartAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding

    private val args: ProductsFragmentArgs by navArgs()

    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = ProductsData.getData(args.categoryName)

        val productsAdapter = ProductsAdapter(productList)
        binding.rvProducts.adapter = productsAdapter

        productsAdapter.onAddCartClick = { product ->

            var count = 1

            db.collection("carts").document(product.name).get()
                .addOnSuccessListener {

                    try {
                        count = (it.get("count") as Long).toInt()
                        count++
                    }   catch (e: Exception) {
                        count = 1
                    }

                    val productTemp = hashMapOf(
                        "name" to product.name,
                        "image" to product.image.toString(),
                        "price" to product.price.toString(),
                        "count" to count
                    )

                    db.collection("carts").document(product.name)
                        .set(productTemp)
                        .addOnSuccessListener {
                            Toast.makeText(requireContext(), "Veri eklendi!", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
        }
    }
}