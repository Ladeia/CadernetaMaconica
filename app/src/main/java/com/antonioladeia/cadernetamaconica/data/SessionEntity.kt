package com.antonioladeia.cadernetamaconica.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session_entity")
data class SessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dataSessao: String,
    val sessao: String,
    val loja: String,
    val oriente: String,
    val potencia: String,
    val rito: String,
    val observacoes: String? = ""
)
