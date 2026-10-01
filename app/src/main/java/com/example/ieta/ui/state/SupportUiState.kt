package com.example.ieta.ui.state

import com.example.ieta.domain.model.SupportTicket
import com.example.ieta.domain.model.TicketPriority

data class SupportUiState(
    val tickets: List<SupportTicket> = emptyList(),
    val selectedTicket: SupportTicket? = null,
    val isCreatingTicket: Boolean = false,
    val subjectInput: String = "",
    val categoryInput: String = "ARIN Engine",
    val priorityInput: TicketPriority = TicketPriority.MEDIUM,
    val descriptionInput: String = "",
    val isSubmitting: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)
