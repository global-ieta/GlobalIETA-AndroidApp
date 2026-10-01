package com.example.ieta.data.repository

import com.example.ieta.domain.model.EarlyAccessRequest
import com.example.ieta.domain.model.EarlyAccessStatus
import com.example.ieta.domain.repository.EarlyAccessRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class MockEarlyAccessRepository : EarlyAccessRepository {

    private val sampleRequests = mutableListOf(
        EarlyAccessRequest(
            id = "EAR-992101",
            fullName = "Elena Rostova",
            email = "e.rostova@quantum-labs.com",
            organization = "Quantum Labs",
            useCase = "Surgical VR training and real-time biomechanical simulation.",
            industry = "Education & Research",
            requestedTier = "Enterprise Core Platinum",
            status = EarlyAccessStatus.APPROVED
        )
    )

    private val requestsFlow = MutableStateFlow<List<EarlyAccessRequest>>(sampleRequests)

    override fun getRequests(): Flow<List<EarlyAccessRequest>> = requestsFlow

    override suspend fun submitRequest(request: EarlyAccessRequest): Result<EarlyAccessRequest> {
        delay(700)
        val current = requestsFlow.value.toMutableList()
        current.add(request)
        requestsFlow.value = current
        return Result.success(request)
    }

    override suspend fun getRequestStatus(email: String): Flow<EarlyAccessRequest?> =
        requestsFlow.map { list -> list.find { it.email.equals(email, ignoreCase = true) } }
}
