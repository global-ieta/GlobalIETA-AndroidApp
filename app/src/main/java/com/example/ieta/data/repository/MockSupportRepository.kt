package com.example.ieta.data.repository

import com.example.ieta.domain.model.SupportTicket
import com.example.ieta.domain.model.TicketMessage
import com.example.ieta.domain.model.TicketPriority
import com.example.ieta.domain.model.TicketStatus
import com.example.ieta.domain.repository.SupportRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class MockSupportRepository : SupportRepository {

    private val initialTickets = listOf(
        SupportTicket(
            id = "tck-101",
            ticketNumber = "TICK-88402",
            subject = "ARIN Pose Alignment Calibration in Multi-User VR",
            category = "ARIN Engine",
            status = TicketStatus.IN_PROGRESS,
            priority = TicketPriority.HIGH,
            messages = listOf(
                TicketMessage(
                    senderName = "Dr. Alex Vance",
                    text = "Encountering 1.8ms spatial drift when synchronizing 8+ headset anchors over CONNECTOR gateway."
                ),
                TicketMessage(
                    senderName = "Global IETA Support Core",
                    text = "Issue escalated to ARIN Spatial Engineering team. Please verify bandwidth allocation on UDP port 8890.",
                    isSystem = true
                )
            )
        ),
        SupportTicket(
            id = "tck-102",
            ticketNumber = "TICK-88405",
            subject = "API Key Rate Limits on CONNECTOR Gateway",
            category = "API & Gateway",
            status = TicketStatus.OPEN,
            priority = TicketPriority.MEDIUM,
            messages = listOf(
                TicketMessage(
                    senderName = "Dr. Alex Vance",
                    text = "Requesting tier upgrade from 1.2M req/sec to 5M req/sec for enterprise stress test."
                )
            )
        )
    )

    private val ticketsFlow = MutableStateFlow<List<SupportTicket>>(initialTickets)

    override fun getTickets(): Flow<List<SupportTicket>> = ticketsFlow

    override fun getTicketById(id: String): Flow<SupportTicket?> =
        ticketsFlow.map { list -> list.find { it.id == id } }

    override suspend fun createTicket(
        subject: String,
        category: String,
        priority: TicketPriority,
        description: String
    ): Result<SupportTicket> {
        delay(500)
        val newTicket = SupportTicket(
            id = "tck-" + System.currentTimeMillis().toString().takeLast(4),
            ticketNumber = "TICK-" + (10000..99999).random(),
            subject = subject,
            category = category,
            priority = priority,
            messages = listOf(
                TicketMessage(
                    senderName = "User",
                    text = description
                )
            )
        )
        val current = ticketsFlow.value.toMutableList()
        current.add(0, newTicket)
        ticketsFlow.value = current
        return Result.success(newTicket)
    }
}
