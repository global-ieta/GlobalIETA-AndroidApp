package com.example.ieta.ui.state

import com.example.ieta.domain.model.EarlyAccessRequest

data class EarlyAccessUiState(
    val requests: List<EarlyAccessRequest> = emptyList(),
    val nameInput: String = "",
    val emailInput: String = "",
    val organizationInput: String = "",
    val useCaseInput: String = "",
    val industryInput: String = "Gaming & Interactive",
    val tierInput: String = "Enterprise Core Platinum",
    val isSubmitting: Boolean = false,
    val isSubmitted: Boolean = false,
    val submittedReferenceId: String? = null,
    val errorMessage: String? = null
)
