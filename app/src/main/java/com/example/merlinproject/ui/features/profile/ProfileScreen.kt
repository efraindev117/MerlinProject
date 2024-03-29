package com.example.merlinproject.ui.features.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProfileScreen(mViewModel: ProfileViewModel = hiltViewModel()) {
    Text(text = mViewModel.userData.username)
    
}