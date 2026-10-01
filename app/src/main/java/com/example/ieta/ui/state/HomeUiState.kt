package com.example.ieta.ui.state

import com.example.ieta.domain.model.Industry
import com.example.ieta.domain.model.Product

data class HomeUiState(
    val featuredProducts: List<Product> = emptyList(),
    val industries: List<Industry> = emptyList(),
    val activeNodesCount: Int = 1845000,
    val activeCountriesCount: Int = 142,
    val averageUptime: String = "99.999%",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
