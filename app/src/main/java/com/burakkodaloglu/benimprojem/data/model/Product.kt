package com.burakkodaloglu.benimprojem.data.model

data class Product(
    val name: String,
    val image: Int,
    val price: Float,
    val count: Int = 0
)