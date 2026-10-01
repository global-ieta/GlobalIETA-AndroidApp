package com.example.ieta.domain.repository

import com.example.ieta.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getCurrentUser(): Flow<User?>
    suspend fun signIn(email: String, password: String): Result<User>
    suspend fun signOut()
    suspend fun updateUserProfile(user: User): Result<User>
}
