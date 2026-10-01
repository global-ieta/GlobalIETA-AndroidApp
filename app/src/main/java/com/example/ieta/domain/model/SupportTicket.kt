package com.example.ieta.domain.model

data class SupportTicket(
    val id: String,
    val ticketNumber: String,
    val subject: String,
    val category: String,
    val status: TicketStatus = TicketStatus.OPEN,
    val priority: TicketPriority = TicketPriority.MEDIUM,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val messages: List<TicketMessage> = emptyList()
)

data class TicketMessage(
    val senderName: String,
    val text: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isSystem: Boolean = false
)

enum class TicketStatus {
    OPEN,
    IN_PROGRESS,
    RESOLVED,
    CLOSED
}

enum class TicketPriority {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL
}
