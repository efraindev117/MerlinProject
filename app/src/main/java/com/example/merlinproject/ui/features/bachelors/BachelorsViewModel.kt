package com.example.merlinproject.ui.features.bachelors

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merlinproject.domain.model.BachelorsModel
import com.example.merlinproject.domain.usescase.users.UsersUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BachelorsViewModel @Inject constructor(private val usersUsesCase: UsersUsesCase) :
    ViewModel() {

    var userData by mutableStateOf(BachelorsModel())
        private set

}