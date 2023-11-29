package com.example.merlinproject.ui.features.bachelors

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.model.campus.CampusModel
import com.example.merlinproject.domain.usescase.campus.CampusUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BachelorsViewModel @Inject constructor(
    private val campusUsesCase: CampusUsesCase
) : ViewModel() {

    var campusResponse by mutableStateOf<Resource<List<CampusModel>>?>(null)

    fun getCampus() = viewModelScope.launch {
        campusResponse = Resource.Loading
        campusUsesCase.getCampusByName().collect() { campusList ->
            campusResponse = campusList
        }
    }

    init {
        getCampus()
    }

}