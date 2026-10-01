package com.example.ieta.domain.repository

import com.example.ieta.domain.model.EarlyAccessRequest
import kotlinx.coroutines.flow.Flow

interface EarlyAccessRepository {
    fun getRequests(): Flow<List<EarlyAccessRequest>>
    suspend fun submitRequest(request: EarlyAccessRequest): Result<EarlyAccessRequest>
    suspend fun getRequestStatus(email: String): Flow<EarlyAccessRequest?>
}
