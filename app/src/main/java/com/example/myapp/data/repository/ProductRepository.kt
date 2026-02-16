package com.example.myapp.data.repository

import com.example.myapp.data.model.Product
import com.example.myapp.data.remote.RetrofitClient

class ProductRepository {

    // Instanciamos la API directamente del objeto RetrofitClient
    private val api = RetrofitClient.api

    // Tu versión simplificada en una sola línea:
    suspend fun getAllProducts(): List<Product> =
        api.getProducts().body()?.results ?: emptyList()


}