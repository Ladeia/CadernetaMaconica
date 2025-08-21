package com.antonioladeia.cadernetamaconica.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Query("SELECT * from session_entity ORDER BY id ASC")
    fun getAllSessions(): Flow<List<SessionEntity>>

    @Query("SELECT * from session_entity WHERE id = :id")
    fun getSession(id: Int): Flow<SessionEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(session: SessionEntity)

    @Update
    suspend fun update(session: SessionEntity)

    @Delete
    suspend fun delete(session: SessionEntity)
}