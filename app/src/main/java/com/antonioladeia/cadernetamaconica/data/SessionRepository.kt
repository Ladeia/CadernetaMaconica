package com.antonioladeia.cadernetamaconica.data
import kotlinx.coroutines.flow.Flow

interface  SessionRepository {

    fun getAllSessionsStream(): Flow<List<SessionEntity>>

    fun getSessionStream(id: Int): Flow<SessionEntity?>

    suspend fun insert(session: SessionEntity)

    suspend fun delete(session: SessionEntity)

    suspend fun update(session: SessionEntity)
}