package com.example.ieta.feature.earlyaccess

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ieta.data.repository.MockEarlyAccessRepository
import com.example.ieta.domain.model.EarlyAccessRequest
import com.example.ieta.domain.repository.EarlyAccessRepository
import com.example.ieta.ui.state.EarlyAccessUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EarlyAccessViewModel(
    private val repository: EarlyAccessRepository = MockEarlyAccessRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(EarlyAccessUiState())
    val uiState: StateFlow<EarlyAccessUiState> = _uiState.asStateFlow()

    fun onNameChange(value: String) { _uiState.update { it.copy(nameInput = value) } }
    fun onEmailChange(value: String) { _uiState.update { it.copy(emailInput = value) } }
    fun onOrgChange(value: String) { _uiState.update { it.copy(organizationInput = value) } }
    fun onUseCaseChange(value: String) { _uiState.update { it.copy(useCaseInput = value) } }
    fun onIndustryChange(value: String) { _uiState.update { it.copy(industryInput = value) } }
    fun onTierChange(value: String) { _uiState.update { it.copy(tierInput = value) } }

    fun submitRequest(
        mobile: String,
        country: String,
        role: String,
        selectedProducts: List<String>,
        orgSize: String,
        message: String
    ) {
        val state = _uiState.value
        if (state.nameInput.isBlank() || state.emailInput.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Full Name and Email are mandatory.") }
            return
        }

        _uiState.update { it.copy(isSubmitting = true, errorMessage = null) }

        viewModelScope.launch {
            val reqId = "EAR-" + (100000..999999).random()
            val newReq = EarlyAccessRequest(
                id = reqId,
                fullName = state.nameInput,
                email = state.emailInput,
                organization = state.organizationInput.ifBlank { "Personal Node" },
                useCase = "[$orgSize // $role // $country] ${state.useCaseInput.ifBlank { message }} Products: ${selectedProducts.joinToString()}",
                industry = state.industryInput,
                requestedTier = state.tierInput
            )

            val result = repository.submitRequest(newReq)
            result.onSuccess {
                _uiState.update { st ->
                    st.copy(
                        isSubmitting = false,
                        isSubmitted = true,
                        submittedReferenceId = reqId
                    )
                }
            }.onFailure { err ->
                _uiState.update { st ->
                    st.copy(
                        isSubmitting = false,
                        errorMessage = err.localizedMessage ?: "Failed to submit Early Access request."
                    )
                }
            }
        }
    }

    fun dismissSuccessDialog() {
        _uiState.update { it.copy(isSubmitted = false) }
    }
}
