package com.antonioladeia.cadernetamaconica.data

class SessionDataFake {
    fun allSessions(): List<SessionEntity>{
        return listOf(
            SessionEntity(
                0,
                "30-04-2025",
                "sessao 01",
                "loja",
                "oriente",
                "potencia",
                "rito",
                "observacoes"
            ),
            SessionEntity(
                1,
                "07-05-2025",
                "sessao 02",
                "loja",
                "oriente",
                "potencia",
                "rito",
                "observacoes"
            ),
            SessionEntity(
                2,
                "14-05-2025",
                "sessao 03",
                "loja",
                "oriente",
                "potencia",
                "rito",
                "observacoes"
            ),
            SessionEntity(
                3,
                "21-05-2025",
                "sessao 04",
                "loja",
                "oriente",
                "potencia",
                "rito",
                "observacoes"
            ),
        )
    }
}