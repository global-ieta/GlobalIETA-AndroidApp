package com.example.ieta.data.repository

import com.example.ieta.domain.model.AuraMessage
import com.example.ieta.domain.model.MessageSender
import com.example.ieta.domain.repository.AuraRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MockAuraRepository : AuraRepository {

    private val initialMessages = listOf(
        AuraMessage(
            id = "msg-1",
            sender = MessageSender.AURA,
            text = "System initialized. I am AURA — Global IETA Quantum Intelligence Core. How can I assist your spatial operations, platform integration, or ecosystem vertical today?",
            suggestedActions = listOf(
                "Explore ARIN Spatial Engine",
                "Request Early Access",
                "Integrate CONNECTOR API",
                "View Industry Solutions"
            ),
            confidence = 0.99f
        )
    )

    private val messagesFlow = MutableStateFlow<List<AuraMessage>>(initialMessages)

    override fun getMessages(): Flow<List<AuraMessage>> = messagesFlow

    override suspend fun sendMessage(userText: String): AuraMessage {
        val userMsg = AuraMessage(
            id = "msg-" + System.currentTimeMillis(),
            sender = MessageSender.USER,
            text = userText
        )
        val currentList = messagesFlow.value.toMutableList()
        currentList.add(userMsg)
        messagesFlow.value = currentList

        // Simulate core quantum processing latency
        delay(800)

        val responseText = generateAuraResponse(userText)
        val auraMsg = AuraMessage(
            id = "msg-aura-" + System.currentTimeMillis(),
            sender = MessageSender.AURA,
            text = responseText,
            suggestedActions = listOf("Show Technical Docs", "Launch Demo", "Contact Support"),
            confidence = 0.97f
        )

        currentList.add(auraMsg)
        messagesFlow.value = currentList
        return auraMsg
    }

    override suspend fun clearHistory() {
        messagesFlow.value = initialMessages
    }

    private fun generateAuraResponse(query: String): String {
        val q = query.lowercase()
        return when {
            q.contains("arin") || q.contains("spatial") || q.contains("xr") ->
                "ARIN Engine operates with sub-2.4ms spatial pose alignment and neural mesh reconstruction. You can inspect its rendering specs in the ARIN Product Module."
            q.contains("early access") || q.contains("register") || q.contains("sign up") ->
                "You can request Early Access directly through the Early Access portal. Enterprise tier requests are typically reviewed within 24 standard operational hours."
            q.contains("connector") || q.contains("api") || q.contains("integration") ->
                "IETA CONNECTOR provides low-latency REST/gRPC gateways bridging legacy enterprise databases to modern spatial nodes. Benchmark throughput exceeds 1.2M req/sec."
            q.contains("price") || q.contains("billing") || q.contains("cost") ->
                "Global IETA offers Tiered Enterprise Licensing based on active concurrent spatial nodes. Visit the Billing module for real-time tier comparisons."
            else ->
                "Acknowledged query regarding '$query'. Analyzing Global IETA knowledge graph... All spatial nodes and vertical frameworks report nominal status. How else can I assist your team?"
        }
    }
}
