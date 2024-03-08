package com.example.merlinproject.ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.merlinproject.ui.features.campus.details.CampusDetailsScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.CampusDetails.route
    ) {
        composable(route = DetailsScreen.CampusDetails.route) {
            CampusDetailsScreen(navController = navController)
        }
    }
}


sealed class DetailsScreen(val route: String) {
    object CampusDetails : DetailsScreen(route = "campus/detail")
}