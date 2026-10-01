package com.example.ieta.domain.repository

import com.example.ieta.domain.model.AuraMessage
import kotlinx.coroutines.flow.Flow

interface AuraRepository {
    fun getMessages(): Flow<List<AuraMessage>>
    suspend fun sendMessage(userText: String): AuraMessage
    suspend fun clearHistory()
}
