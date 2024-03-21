package com.example.merlinproject.domain.model.campus

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
@Immutable
data class CampusUniversityModel(
    val lazyId: String = UUID.randomUUID().toString(),
    val id: Int = 0,
    val campusName: String = "",
    val campusAddress: String = "",
    val campusPhone: String = "",
    val campusPhoneExt: String = "",
    val campusEmail: String = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val campusServices: List<String> = emptyList()
) : Parcelable