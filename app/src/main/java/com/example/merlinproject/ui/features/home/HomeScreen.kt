package com.example.merlinproject.ui.features.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.merlinproject.ui.navigation.composable.NavigationMD3

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
        NavigationMD3(navController)
}