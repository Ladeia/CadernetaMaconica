package com.antonioladeia.cadernetamaconica.ui.session

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonioladeia.cadernetamaconica.data.SessionRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SessionEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: SessionRepository
) : ViewModel() {


    var sessionUiState by mutableStateOf(SessionUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[SessionEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            sessionUiState = itemsRepository.getSessionStream(itemId)
                .filterNotNull()
                .first()
                .toSessionUiState(true)
        }
    }

    suspend fun updateItem() {
        if (validateInput(sessionUiState.sessionDetails)) {
            itemsRepository.update(sessionUiState.sessionDetails.toSession())
        }
    }

    fun updateUiState(sessionDetails: SessionDetails) {
        sessionUiState =
            SessionUiState(sessionDetails = sessionDetails, isEntryValid = validateInput(sessionDetails))
    }

    private fun validateInput(uiState: SessionDetails = sessionUiState.sessionDetails): Boolean {
        return with(uiState) {
            loja.isNotBlank() && potencia.isNotBlank() && sessao.isNotBlank()
        }
    }
}
