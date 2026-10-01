package com.example.ieta.ui.state

import com.example.ieta.domain.model.AuraMessage

data class AuraUiState(
    val messages: List<AuraMessage> = emptyList(),
    val inputText: String = "",
    val isProcessing: Boolean = false,
    val quickPrompts: List<String> = listOf(
        "Explain ARIN Engine spatial pose sync",
        "How do I connect CONNECTOR API to SAP?",
        "Show Early Access requirements",
        "Compare Enterprise Pricing Tiers"
    ),
    val errorMessage: String? = null
)
