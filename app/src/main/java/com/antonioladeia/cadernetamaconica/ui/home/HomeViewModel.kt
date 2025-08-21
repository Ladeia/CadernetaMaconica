package com.antonioladeia.cadernetamaconica.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonioladeia.cadernetamaconica.data.SessionEntity
import com.antonioladeia.cadernetamaconica.data.SessionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class HomeViewModel(sessionsRepository: SessionRepository) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        sessionsRepository.getAllSessionsStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class HomeUiState(val sessionsList: List<SessionEntity> = listOf())
