package com.example.myapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.myapp.data.model.Product
import com.example.myapp.viewmodel.ProductViewModel
import com.example.myapp.viewmodel.UiState
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ProductScreen(viewModel: ProductViewModel = viewModel()) {
        val state = viewModel.state

        Scaffold(
            containerColor = Color.Black,

            topBar = {
                TopAppBar(
                    title = { Text("Tienda Online",
                        fontWeight = FontWeight.Bold
                    ) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                when (state) {
                    is UiState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is UiState.Error -> {
                        Text(
                            text = (state as UiState.Error).message,
                            color = Color.Red
                        )
                    }
                    is UiState.Success -> {
                        // Pintamos la lista de productos
                        ProductList(products = (state as UiState.Success).data)
                    }
                }
            }
        }
    }

    @Composable
    fun ProductList(products: List<Product>) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products) { product ->
                ProductItem(product)
            }
        }
    }

    @Composable
    fun ProductItem(product: Product) {
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // IMAGEN (Usando Coil)
                AsyncImage(
                    model = product.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 16.dp),
                    contentScale = ContentScale.Crop
                )

                // TEXTOS
                Column {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${product.price} €",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
