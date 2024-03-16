package com.example.merlinproject.ui.navigation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.merlinproject.ui.features.calendar.CalendarScreen
import com.example.merlinproject.ui.features.campus.CampusScreen
import com.example.merlinproject.ui.features.help.HelperScreen
import com.example.merlinproject.ui.features.notes.NotesScreen
import com.example.merlinproject.ui.features.ratings.RatingsScreen
import com.example.merlinproject.ui.features.resume.ResumeScreen
import com.example.merlinproject.ui.features.settings.ConfigScreen
import com.example.merlinproject.ui.features.subject.SubjectScreen
import com.example.merlinproject.ui.features.teacher.TeachersScreen
import com.example.merlinproject.ui.features.time.TimeScreen

@Composable
fun HomeDrawerNavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = HomeDrawerScreen.ResumeScreen.route
    ) {
        composable(route = HomeDrawerScreen.ResumeScreen.route) {
            ResumeScreen(navController = navHostController,paddingValues = paddingValues)
        }

        composable(route = HomeDrawerScreen.NotesScreen.route) {
            NotesScreen(navController = navHostController)
        }

        composable(route = HomeDrawerScreen.TimeScreen.route) {
            TimeScreen(navController = navHostController)
        }

        composable(route = HomeDrawerScreen.CalendarScreen.route) {
            CalendarScreen(navController = navHostController)
        }
        composable(route = HomeDrawerScreen.RatingsScreen.route) {
            RatingsScreen(navController = navHostController)
        }

        composable(route = HomeDrawerScreen.SubjectScreen.route) {
            SubjectScreen(navController = navHostController)
        }

        composable(route = HomeDrawerScreen.TeachersScreen.route) {
            TeachersScreen(navController = navHostController, paddingValues = paddingValues)
        }

        composable(route = HomeDrawerScreen.CampusScreen.route) {
            CampusScreen(navController = navHostController, paddingValues = paddingValues)
        }

        composable(route = HomeDrawerScreen.HelperScreen.route) {
            HelperScreen(navController = navHostController, paddingValues = paddingValues)
        }

        composable(route = HomeDrawerScreen.ConfigScreen.route) {
            ConfigScreen(navController = navHostController,paddingValues = paddingValues)
        }

        detailsNavGraph(navHostController)
    }
}

sealed class HomeDrawerScreen(val route: String) {
    object ResumeScreen : HomeDrawerScreen(route = "resume_screen")
    object NotesScreen : HomeDrawerScreen(route = "notes_screen")
    object TimeScreen : HomeDrawerScreen(route = "time_screen")
    object CalendarScreen : HomeDrawerScreen(route = "calendar_screen")
    object RatingsScreen : HomeDrawerScreen(route = "ratings_screen")
    object SubjectScreen : HomeDrawerScreen(route = "subject_screen")
    object TeachersScreen : HomeDrawerScreen(route = "teachers_screen")
    object CampusScreen : HomeDrawerScreen(route = "campus_screen")
    object HelperScreen : HomeDrawerScreen(route = "helper_screen")
    object ConfigScreen : HomeDrawerScreen(route = "config_screen")
}