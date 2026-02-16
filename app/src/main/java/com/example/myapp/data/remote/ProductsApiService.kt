package com.example.myapp.data.remote

import com.example.myapp.data.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApiService {
    @GET("api/products")
    suspend fun getProducts(): Response<ProductResponse>
}