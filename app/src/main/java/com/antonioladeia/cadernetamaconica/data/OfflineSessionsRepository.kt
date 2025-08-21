package com.antonioladeia.cadernetamaconica.data

import kotlinx.coroutines.flow.Flow

class OfflineSessionsRepository(private val sessionDao: SessionDao) : SessionRepository {
    override fun getAllSessionsStream(): Flow<List<SessionEntity>> = sessionDao.getAllSessions()

    override fun getSessionStream(id: Int): Flow<SessionEntity?> = sessionDao.getSession(id)

    override suspend fun insert(session: SessionEntity) = sessionDao.insert(session)

    override suspend fun delete(session: SessionEntity) = sessionDao.delete(session)

    override suspend fun update(session: SessionEntity) = sessionDao.update(session)
}
