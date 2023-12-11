package com.example.merlinproject.domain.model.campus

data class CampusModel(
    val id: Int = 1,
    val name: String = "",
    val telephone: String = "",
    val url: String = "",
    val email: String = "",
    val ofertaAcademica: List<OfertaAcademica> = emptyList()
)

data class OfertaAcademica(
    val licenciatura2: String = "",
    val licenciaturas: List<String> = emptyList()
)