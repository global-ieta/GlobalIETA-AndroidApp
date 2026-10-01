package com.example.ieta.ui.state

import com.example.ieta.domain.model.Industry
import com.example.ieta.domain.model.Product

data class ProductUiState(
    val products: List<Product> = emptyList(),
    val industries: List<Industry> = emptyList(),
    val selectedProduct: Product? = null,
    val selectedIndustryId: String? = null,
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
