package com.example.ieta.domain.model

data class User(
    val id: String,
    val fullName: String,
    val email: String,
    val role: String = "Enterprise Architect",
    val organization: String = "Global IETA Systems",
    val avatarUrl: String? = null,
    val isVerified: Boolean = true,
    val activeSubscription: String = "GLOBAL CORE PLATINUM"
)
