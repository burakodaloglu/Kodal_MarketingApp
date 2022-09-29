package com.burakkodaloglu.benimprojem.presentation.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.burakkodaloglu.benimprojem.data.model.Product
import com.burakkodaloglu.benimprojem.databinding.FragmentCartBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private var db = Firebase.firestore

    private val cartAdapter = CartAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listenData()

        cartAdapter.onAddClick = {
            addProduct(it)
        }

        cartAdapter.onDeleteClick = {
            deleteProduct(it)
        }
    }

    private fun listenData() {

        db.collection("carts").addSnapshotListener { snapshot, e ->

            if (e != null) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            val tempList = arrayListOf<Product>()

            snapshot?.forEach { document ->
                tempList.add(
                    Product(
                        document.get("name") as String,
                        (document.get("image") as String).toInt(),
                        (document.get("price") as String).toFloat(),
                        (document.get("count") as Long).toInt(),
                    )
                )
            }

            cartAdapter.updateList(tempList)
            binding.rvCartProducts.adapter = cartAdapter

            var totalPrice = 0.0

            tempList.forEach { product ->
                totalPrice += product.price * product.count
            }

            binding.tvTotalPrice.text = "$totalPrice ₺"
        }
    }

    private fun addProduct(name: String) {

        var count = 1

        db.collection("carts").document(name).get()
            .addOnSuccessListener {

                count = (it.get("count") as Long).toInt()
                count++

                updateProduct(name, count)
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
    }

    private fun deleteProduct(name: String) {

        var count = 1

        db.collection("carts").document(name).get()
            .addOnSuccessListener {

                count = (it.get("count") as Long).toInt()

                if (count == 1) {

                    db.collection("carts").document(name).delete()
                        .addOnSuccessListener {

                        }.addOnFailureListener {

                        }
                } else {
                    count--
                    updateProduct(name, count)
                }

            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateProduct(name: String, count: Int) {
        db.collection("carts").document(name)
            .update("count", count)
            .addOnSuccessListener {

            }.addOnFailureListener {

            }
    }
}