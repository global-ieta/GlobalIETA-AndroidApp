package com.example.ieta.feature.aura

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ieta.data.repository.MockAuraRepository
import com.example.ieta.domain.repository.AuraRepository
import com.example.ieta.ui.state.AuraUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuraViewModel(
    private val auraRepository: AuraRepository = MockAuraRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuraUiState())
    val uiState: StateFlow<AuraUiState> = _uiState.asStateFlow()

    init {
        observeMessages()
    }

    private fun observeMessages() {
        viewModelScope.launch {
            auraRepository.getMessages().collect { messages ->
                _uiState.update { it.copy(messages = messages) }
            }
        }
    }

    fun onInputTextChange(text: String) {
        _uiState.update { it.copy(inputText = text) }
    }

    fun sendMessage(text: String? = null) {
        val queryToSend = text ?: _uiState.value.inputText
        if (queryToSend.isBlank()) return

        _uiState.update {
            it.copy(
                inputText = "",
                isProcessing = true,
                errorMessage = null
            )
        }

        viewModelScope.launch {
            try {
                auraRepository.sendMessage(queryToSend)
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorMessage = e.localizedMessage ?: "Failed to transmit message to AURA core.")
                }
            } finally {
                _uiState.update { it.copy(isProcessing = false) }
            }
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            auraRepository.clearHistory()
        }
    }
}
