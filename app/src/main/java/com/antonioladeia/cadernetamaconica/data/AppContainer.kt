package com.antonioladeia.cadernetamaconica.data

import android.content.Context

interface AppContainer {
    val sessionRepository: SessionRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val sessionRepository: SessionRepository by lazy {
        OfflineSessionsRepository(CadernetaDatabase.getDatabase(context).sessionDao())
    }
}