package com.example.ieta.domain.model

data class Product(
    val id: String,
    val name: String,
    val code: String,
    val tagline: String,
    val description: String,
    val industry: String,
    val iconName: String,
    val accentColorHex: String = "#00D9FF",
    val status: ProductStatus = ProductStatus.ACTIVE,
    val features: List<String> = emptyList(),
    val metrics: Map<String, String> = emptyMap(),
    val rating: Double = 4.9,
    val downloads: Int = 125000
)

enum class ProductStatus {
    ACTIVE,
    BETA,
    ENTERPRISE,
    UPCOMING
}
