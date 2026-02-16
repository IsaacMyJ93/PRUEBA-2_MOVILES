package com.example.myapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.model.Product
import com.example.myapp.data.repository.ProductRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue

class ProductViewModel : ViewModel(){
    // Instancia simple del repositorio
    private val repository = ProductRepository()

    // Variable de estado (igual que en tu ejemplo)
    // Empieza en Loading para que salga el círculo nada más abrir
    var state by mutableStateOf<UiState<List<Product>>>(UiState.Loading)
        private set

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            state = UiState.Loading // Ponemos el "reloj de arena"

            state = try {
                // Llamamos al repo
                val result = repository.getAllProducts()

                if (result.isNotEmpty()) {
                    UiState.Success(result) // ¡Éxito!
                } else {
                    UiState.Error("La lista está vacía")
                }
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Error desconocido cargando productos")
            }
        }
    }
}