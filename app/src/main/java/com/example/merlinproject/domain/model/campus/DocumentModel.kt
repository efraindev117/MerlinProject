package com.example.merlinproject.domain.model.campus

data class DocumentModel(
    val id: Int = 0,
    val name: String = "",
    var studios_list: ArrayList<StudentItem> = arrayListOf()
)

data class StudentItem(
    val academy: String = ""
)




