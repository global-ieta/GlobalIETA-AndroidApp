package com.example.ieta.domain.model

data class Industry(
    val id: String,
    val name: String,
    val code: String,
    val description: String,
    val iconName: String,
    val accentColorHex: String = "#00D4FF",
    val productCount: Int = 0,
    val heroImage: String = ""
)
