package com.example.myapp.data.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("results") val results: List<Product>
)
data class Product(
    val _id: String,          // El identificador único
    val name: String,         // Nombre del producto
    val description: String,  // Descripción
    val price: Double,        // Precio (Cuidado: es Double, no String)
    val category: String,     // Categoría
    val image: String,        // URL de la foto
    val active: Boolean       // Si está activo o no
)