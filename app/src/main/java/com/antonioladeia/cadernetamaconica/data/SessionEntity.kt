package com.antonioladeia.cadernetamaconica.data

data class SessionEntity(
    val id: Int = 0,
    val dataSessao: String,
    val sessao: String,
    val loja: String,
    val oriente: String,
    val potencia: String,
    val rito: String,
    val observacoes: String? = ""
)
