package com.example.ieta.data.repository

import com.example.ieta.domain.model.User
import com.example.ieta.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MockAuthRepository : AuthRepository {

    private val defaultUser = User(
        id = "usr-001",
        fullName = "Dr. Alex Vance",
        email = "alex.vance@global-ieta.io",
        role = "Chief Spatial Architect",
        organization = "Nexus Global Technologies",
        isVerified = true,
        activeSubscription = "GLOBAL CORE PLATINUM"
    )

    private val userFlow = MutableStateFlow<User?>(defaultUser)

    override fun getCurrentUser(): Flow<User?> = userFlow

    override suspend fun signIn(email: String, password: String): Result<User> {
        delay(600)
        return if (email.contains("@") && password.length >= 4) {
            val formattedName = email.substringBefore("@")
                .replace(".", " ")
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

            val user = User(
                id = "usr-" + System.currentTimeMillis().toString().takeLast(4),
                fullName = formattedName,
                email = email,
                role = "Enterprise Operator",
                organization = "Global Enterprise Corp"
            )
            userFlow.value = user
            Result.success(user)
        } else {
            Result.failure(IllegalArgumentException("Invalid system credentials or authorization signature."))
        }
    }

    override suspend fun signOut() {
        delay(300)
        userFlow.value = null
    }

    override suspend fun updateUserProfile(user: User): Result<User> {
        delay(400)
        userFlow.value = user
        return Result.success(user)
    }
}
