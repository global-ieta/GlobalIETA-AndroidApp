package com.example.ieta.feature.support

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ieta.data.repository.MockSupportRepository
import com.example.ieta.domain.model.TicketPriority
import com.example.ieta.domain.repository.SupportRepository
import com.example.ieta.ui.state.SupportUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SupportViewModel(
    private val supportRepository: SupportRepository = MockSupportRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(SupportUiState())
    val uiState: StateFlow<SupportUiState> = _uiState.asStateFlow()

    init {
        observeTickets()
    }

    private fun observeTickets() {
        viewModelScope.launch {
            supportRepository.getTickets().collect { tickets ->
                _uiState.update { it.copy(tickets = tickets) }
            }
        }
    }

    fun onSubjectChange(value: String) { _uiState.update { it.copy(subjectInput = value) } }
    fun onCategoryChange(value: String) { _uiState.update { it.copy(categoryInput = value) } }
    fun onPriorityChange(priority: TicketPriority) { _uiState.update { it.copy(priorityInput = priority) } }
    fun onDescriptionChange(value: String) { _uiState.update { it.copy(descriptionInput = value) } }

    fun openCreateTicketModal() {
        _uiState.update { it.copy(isCreatingTicket = true, errorMessage = null) }
    }

    fun closeCreateTicketModal() {
        _uiState.update { it.copy(isCreatingTicket = false) }
    }

    fun createTicket() {
        val state = _uiState.value
        if (state.subjectInput.isBlank() || state.descriptionInput.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Subject and Description are required.") }
            return
        }

        _uiState.update { it.copy(isSubmitting = true, errorMessage = null) }

        viewModelScope.launch {
            val result = supportRepository.createTicket(
                subject = state.subjectInput,
                category = state.categoryInput,
                priority = state.priorityInput,
                description = state.descriptionInput
            )

            result.onSuccess {
                _uiState.update { st ->
                    st.copy(
                        isSubmitting = false,
                        isCreatingTicket = false,
                        subjectInput = "",
                        descriptionInput = "",
                        successMessage = "Ticket submitted successfully."
                    )
                }
            }.onFailure { err ->
                _uiState.update { st ->
                    st.copy(
                        isSubmitting = false,
                        errorMessage = err.localizedMessage ?: "Failed to create ticket."
                    )
                }
            }
        }
    }
}
