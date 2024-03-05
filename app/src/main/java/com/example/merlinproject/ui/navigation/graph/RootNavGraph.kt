package com.example.merlinproject.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.merlinproject.ui.features.home.HomeScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavGraph(navController)
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}
object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}