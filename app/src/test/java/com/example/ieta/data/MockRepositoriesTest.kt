package com.example.ieta.data

import com.example.ieta.data.repository.MockAuraRepository
import com.example.ieta.data.repository.MockAuthRepository
import com.example.ieta.data.repository.MockEarlyAccessRepository
import com.example.ieta.data.repository.MockProductRepository
import com.example.ieta.data.repository.MockSupportRepository
import com.example.ieta.domain.model.EarlyAccessRequest
import com.example.ieta.domain.model.TicketPriority
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class MockRepositoriesTest {

    @Test
    fun productRepository_returnsProductsAndIndustries() = runTest {
        val repo = MockProductRepository()
        val products = repo.getProducts().first()
        val industries = repo.getIndustries().first()

        assertTrue(products.isNotEmpty())
        assertTrue(industries.isNotEmpty())

        val arinProduct = repo.getProductById("arin").first()
        assertNotNull(arinProduct)
        assertEquals("ARIN Engine", arinProduct?.name)

        val searchResult = repo.searchProducts("spatial").first()
        assertTrue(searchResult.isNotEmpty())
    }

    @Test
    fun auraRepository_sendsMessageAndReceivesResponse() = runTest {
        val repo = MockAuraRepository()
        val initialMessages = repo.getMessages().first()
        assertEquals(1, initialMessages.size)

        repo.sendMessage("Tell me about ARIN spatial engine")
        val updatedMessages = repo.getMessages().first()
        assertEquals(3, updatedMessages.size)
        assertTrue(updatedMessages.last().text.contains("ARIN Engine operates"))
    }

    @Test
    fun authRepository_signInAndProfileManagement() = runTest {
        val repo = MockAuthRepository()
        val currentUser = repo.getCurrentUser().first()
        assertNotNull(currentUser)

        val signInResult = repo.signIn("test.user@global-ieta.io", "password123")
        assertTrue(signInResult.isSuccess)
        assertEquals("test.user@global-ieta.io", repo.getCurrentUser().first()?.email)

        repo.signOut()
        assertEquals(null, repo.getCurrentUser().first())
    }

    @Test
    fun earlyAccessRepository_submitsAndRetrievesRequest() = runTest {
        val repo = MockEarlyAccessRepository()
        val initialRequests = repo.getRequests().first()
        assertTrue(initialRequests.isNotEmpty())

        val newReq = EarlyAccessRequest(
            fullName = "John Doe",
            email = "johndoe@test.com",
            organization = "Test Corp",
            useCase = "Testing spatial nodes",
            industry = "Gaming & Interactive"
        )
        val submitResult = repo.submitRequest(newReq)
        assertTrue(submitResult.isSuccess)

        val foundReq = repo.getRequestStatus("johndoe@test.com").first()
        assertNotNull(foundReq)
        assertEquals("John Doe", foundReq?.fullName)
    }

    @Test
    fun supportRepository_createsAndRetrievesTicket() = runTest {
        val repo = MockSupportRepository()
        val tickets = repo.getTickets().first()
        assertTrue(tickets.isNotEmpty())

        val createResult = repo.createTicket(
            subject = "Latency Issue",
            category = "ARIN Engine",
            priority = TicketPriority.HIGH,
            description = "Observed 10ms spike in frame render."
        )
        assertTrue(createResult.isSuccess)
        val createdTicket = createResult.getOrNull()
        assertNotNull(createdTicket)

        val fetchedTicket = repo.getTicketById(createdTicket!!.id).first()
        assertNotNull(fetchedTicket)
        assertEquals("Latency Issue", fetchedTicket?.subject)
    }
}
