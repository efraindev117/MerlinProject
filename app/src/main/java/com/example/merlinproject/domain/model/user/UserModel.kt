package com.example.merlinproject.domain.model.user

data class UserModel(
    var id: String = "",
    var username: String = "",
    var email: String = "",
    var password: String = "",
    val profileImg: String = "",
    val profession: String = "",
    val campusIns: String = ""
)
