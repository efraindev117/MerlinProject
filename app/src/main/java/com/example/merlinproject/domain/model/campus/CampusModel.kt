package com.example.merlinproject.domain.model.campus

data class CampusModel(
    val id: Int = 1,
    val firestoreDocumentID: String = "",
    val name: String = "",
    val telephone: String = "",
    val url: String = "",
    val email: String = "",
    val cienciasSociales: List<String> = emptyList(),
    val ofertaAcademica: List<String> = emptyList(),
    //val ofertaAcademica: List<OfertaAcademica> = emptyList()
)

data class OfertaAcademica(
    val licenciaturas: List<String> = emptyList()
)