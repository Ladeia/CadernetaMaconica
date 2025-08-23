package com.antonioladeia.cadernetamaconica.ui.session

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.antonioladeia.cadernetamaconica.data.SessionEntity
import com.antonioladeia.cadernetamaconica.data.SessionRepository
import java.time.LocalDate

class SessionEntryViewModel(private val sessionRepository: SessionRepository) : ViewModel() {

    var sessionUiState by mutableStateOf(SessionUiState())
        private set

    fun updateUiState(sessionDetails: SessionDetails) {
        sessionUiState =
            SessionUiState(sessionDetails = sessionDetails, isEntryValid = validateInput(sessionDetails))
    }

    suspend fun saveItem() {
        if (validateInput()) {
            sessionRepository.insert(sessionUiState.sessionDetails.toSession())
        }
    }

    private fun validateInput(uiState: SessionDetails = sessionUiState.sessionDetails): Boolean {
        return with(uiState) {
            loja.isNotBlank() && potencia.isNotBlank() && sessao.isNotBlank()
        }
    }
}

data class SessionUiState(
    val sessionDetails: SessionDetails = SessionDetails(),
    val isEntryValid: Boolean = false
)

data class SessionDetails(
    val id: Int = 0,
    val dataSessao: String = "",
    val sessao: String = "",
    val grau: String = "",
    val loja: String = "",
    val oriente: String = "",
    val potencia: String = "",
    val rito: String = "",
    val observacoes: String = ""

)

fun SessionDetails.toSession(): SessionEntity = SessionEntity(
    id = id,
    dataSessao = dataSessao,
    sessao = sessao,
    grau = grau,
    loja = loja,
    oriente = oriente,
    potencia = potencia,
    rito = rito,
    observacoes = observacoes
)

fun SessionEntity.toSessionUiState(isEntryValid: Boolean = false): SessionUiState = SessionUiState(
    sessionDetails = this.toSessionDetails(),
    isEntryValid = isEntryValid
)


fun SessionEntity.toSessionDetails(): SessionDetails = SessionDetails(
    id = id,
    dataSessao = dataSessao,
    sessao = sessao,
    grau = grau,
    loja = loja,
    oriente = oriente,
    potencia = potencia,
    rito = rito,
    observacoes = observacoes ?: ""
)
