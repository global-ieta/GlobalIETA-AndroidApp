package com.example.ieta.domain.model

data class EarlyAccessRequest(
    val id: String = "EAR-" + System.currentTimeMillis().toString().takeLast(6),
    val fullName: String,
    val email: String,
    val organization: String,
    val useCase: String,
    val industry: String,
    val requestedTier: String = "Enterprise Core",
    val status: EarlyAccessStatus = EarlyAccessStatus.PENDING,
    val submittedAt: Long = System.currentTimeMillis()
)

enum class EarlyAccessStatus {
    PENDING,
    REVIEWING,
    APPROVED,
    WAITLISTED
}
