package com.example.merlinproject.ui.features.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.merlinproject.ui.navigation.composable.BottomNavigationBar
import com.example.merlinproject.ui.navigation.composable.NavigationMD3
import com.example.merlinproject.ui.navigation.graph.HomeDrawerNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
        NavigationMD3(navController)
}