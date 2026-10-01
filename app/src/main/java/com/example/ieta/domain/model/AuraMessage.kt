package com.example.ieta.domain.model

data class AuraMessage(
    val id: String,
    val sender: MessageSender,
    val text: String,
    val timestamp: Long = System.currentTimeMillis(),
    val suggestedActions: List<String> = emptyList(),
    val codeSnippet: String? = null,
    val confidence: Float = 0.98f
)

enum class MessageSender {
    USER,
    AURA
}
