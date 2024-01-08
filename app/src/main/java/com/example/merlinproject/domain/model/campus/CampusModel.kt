package com.example.merlinproject.domain.model.campus

data class CampusModel(
    val id: Int = 1,
    val firestoreDocumentID: String = "",
    val campus_address: String = "",
    val name: String = "",
    val campus_phone: String = "",
    val url: String = "",
    val image: String = "",
    val campus_email: String = "",
    val latitud: Double = 0.0,
    val longitud: Double = 0.0,
    val cienciasSociales: List<String> = emptyList(),
    val ofertaAcademica: List<String> = emptyList(),
    //val campus_services: List<CampusService> = emptyList()
    //val ofertaAcademica: List<OfertaAcademica> = emptyList()
)

data class OfertaAcademica(
    val licenciaturas: List<String> = emptyList()
)

data class CampusService(
    val dining_room: String = "",
    val healt_service: String = "",
    val internet: String = "",
    val parking_service: String = "",
    val room: String = ""
)