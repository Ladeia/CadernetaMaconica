package com.antonioladeia.cadernetamaconica.ui.session

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonioladeia.cadernetamaconica.data.SessionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SessionDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val sessionRepository: SessionRepository,
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[SessionDetailsDestination.itemIdArg])

    val uiState: StateFlow<SessionDetailsUiState> =
        sessionRepository.getSessionStream(itemId)
            .filterNotNull()
            .map {
                SessionDetailsUiState(sessionDetails = it.toSessionDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = SessionDetailsUiState()
            )

    suspend fun deleteItem() {
        sessionRepository.delete(uiState.value.sessionDetails.toSession())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for ItemDetailsScreen
 */
data class SessionDetailsUiState(
    val sessionDetails: SessionDetails = SessionDetails()
)
