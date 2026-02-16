package com.example.myapp.viewmodel

interface UiState<out T> {
    object Loading : UiState<Nothing>                // Cargando...
    data class Success<T>(val data: T) : UiState<T>  // ¡Datos listos!
    data class Error(val message: String) : UiState<Nothing> // Hubo un problema
}