package com.example.merlinproject.ui.features.campus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.model.campus.CampusUniversityModel
import com.example.merlinproject.domain.usescase.NewCampusUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CampusViewModel @Inject constructor(
    private val newCampusUsesCase: NewCampusUsesCase
) : ViewModel() {

    var campusResponse by mutableStateOf<Resource<List<CampusUniversityModel>>?>(null)

    private fun getCampus() = viewModelScope.launch {
        newCampusUsesCase.newGetCampus().collect() { response ->
            campusResponse = response
        }
    }

    init {
        getCampus()
    }
}