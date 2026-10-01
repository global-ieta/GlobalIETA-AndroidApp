package com.example.ieta.domain.repository

import com.example.ieta.domain.model.SupportTicket
import com.example.ieta.domain.model.TicketPriority
import kotlinx.coroutines.flow.Flow

interface SupportRepository {
    fun getTickets(): Flow<List<SupportTicket>>
    fun getTicketById(id: String): Flow<SupportTicket?>
    suspend fun createTicket(
        subject: String,
        category: String,
        priority: TicketPriority,
        description: String
    ): Result<SupportTicket>
}
