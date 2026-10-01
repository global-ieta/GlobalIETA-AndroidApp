package com.example.ieta.domain.repository

import com.example.ieta.domain.model.Industry
import com.example.ieta.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<List<Product>>
    fun getProductById(id: String): Flow<Product?>
    fun getProductsByIndustry(industryId: String): Flow<List<Product>>
    fun getIndustries(): Flow<List<Industry>>
    fun searchProducts(query: String): Flow<List<Product>>
}
