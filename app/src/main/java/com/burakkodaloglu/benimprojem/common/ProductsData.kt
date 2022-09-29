package com.burakkodaloglu.benimprojem.common

import com.burakkodaloglu.benimprojem.R
import com.burakkodaloglu.benimprojem.data.model.Product

object ProductsData {

    fun getData(categoryName: String): List<Product> {

        return when (categoryName) {
            "Elektronik,Cep Telefonu" -> getElectronicData()
            "Ev, Yaşam, Ofis, Kırtasiye" -> getHomeData()
            "Anne, Bebek, Oyyuncak" -> getBabyData()
            "Moda, Ayakkabı" -> getFashionData()
            "Kitap, Müzik, Hobi" -> getBookData()
            "Spor, Outdoor" -> getSportData()
            "Sağlık, Kozmetik" -> getHealtData()
            "Bahçe, Yapı Market" -> getGardenData()
            "Petshop" -> getPetshopData()
            else -> listOf()
        }
    }

    private fun getElectronicData(): List<Product> {
        return listOf(
            Product("Samsung Galaxy Note 10 Lite", R.drawable.samsung, 15.00f),
            Product("Apple iPhone 13 Pro Max", R.drawable.iphone, 34.000f),
            Product("Oppo A15", R.drawable.oppo, 34.000f),
            Product("Electronic 4", R.drawable.phone, 34.000f),
            Product("Electronic 5", R.drawable.phone, 34.000f),
            Product("Electronic 6", R.drawable.phone, 34.000f)
        )
    }

    private fun getHomeData(): List<Product> {
        return listOf(
            Product("Home 1", R.drawable.home, 4.000f),
            Product("Home 2", R.drawable.home, 4.000f),
            Product("Home 3", R.drawable.home, 4.000f),
            Product("Home 4", R.drawable.home, 4.000f),
            Product("Home 5", R.drawable.home, 4.000f),
            Product("Home 6", R.drawable.home, 4.000f)
        )
    }

    private fun getBabyData(): List<Product> {
        return listOf(
            Product("Baby 1", R.drawable.baby, 1.000000f),
            Product("Baby 2", R.drawable.home, 1.000000f),
            Product("Baby 3", R.drawable.home, 1.000000f),
            Product("Baby 4", R.drawable.home, 1.000000f),
            Product("Baby 5", R.drawable.home, 1.000000f),
            Product("Baby 6", R.drawable.home, 1.000000f)
        )
    }

    private fun getFashionData(): List<Product> {
        return listOf(
            Product("Baby 1", R.drawable.baby, 1.000000f),
            Product("Baby 2", R.drawable.home, 1.000000f),
            Product("Baby 3", R.drawable.home, 1.000000f),
            Product("Baby 4", R.drawable.home, 1.000000f),
            Product("Baby 5", R.drawable.home, 1.000000f),
            Product("Baby 6", R.drawable.home, 1.000000f)
        )
    }

    private fun getBookData(): List<Product> {
        return listOf(
            Product("Baby 1", R.drawable.baby, 1.000000f),
            Product("Baby 2", R.drawable.home, 1.000000f),
            Product("Baby 3", R.drawable.home, 1.000000f),
            Product("Baby 4", R.drawable.home, 1.000000f),
            Product("Baby 5", R.drawable.home, 1.000000f),
            Product("Baby 6", R.drawable.home, 1.000000f)
        )
    }

    private fun getSportData(): List<Product> {
        return listOf(
            Product("Baby 1", R.drawable.baby, 1.000000f),
            Product("Baby 2", R.drawable.home, 1.000000f),
            Product("Baby 3", R.drawable.home, 1.000000f),
            Product("Baby 4", R.drawable.home, 1.000000f),
            Product("Baby 5", R.drawable.home, 1.000000f),
            Product("Baby 6", R.drawable.home, 1.000000f)
        )
    }

    private fun getHealtData(): List<Product> {
        return listOf(
            Product("Baby 1", R.drawable.baby, 1.000000f),
            Product("Baby 2", R.drawable.home, 1.000000f),
            Product("Baby 3", R.drawable.home, 1.000000f),
            Product("Baby 4", R.drawable.home, 1.000000f),
            Product("Baby 5", R.drawable.home, 1.000000f),
            Product("Baby 6", R.drawable.home, 1.000000f)
        )
    }

    fun getGardenData(): List<Product> {
        return listOf(
            Product("Baby 1", R.drawable.baby, 1.000000f),
            Product("Baby 2", R.drawable.home, 1.000000f),
            Product("Baby 3", R.drawable.home, 1.000000f),
            Product("Baby 4", R.drawable.home, 1.000000f),
            Product("Baby 5", R.drawable.home, 1.000000f),
            Product("Baby 6", R.drawable.home, 1.000000f)
        )
    }

    private fun getPetshopData(): List<Product> {
        return listOf(
            Product("Baby 1", R.drawable.baby, 1.000000f),
            Product("Baby 2", R.drawable.home, 1.000000f),
            Product("Baby 3", R.drawable.home, 1.000000f),
            Product("Baby 4", R.drawable.home, 1.000000f),
            Product("Baby 5", R.drawable.home, 1.000000f),
            Product("Baby 6", R.drawable.home, 1.000000f)
        )
    }
}