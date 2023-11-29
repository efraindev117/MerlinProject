package com.example.merlinproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.merlinproject.ui.features.auth.welcome.WelcomeScreen
import com.example.merlinproject.ui.features.auth.login.LoginScreen
import com.example.merlinproject.ui.features.auth.register.RegisterScreen
import com.example.merlinproject.ui.features.bachelors.BachelorsScreen

@Composable
fun NestedNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreensNavigation.WelcomeScreen.route
    ) {
        composable(route = ScreensNavigation.WelcomeScreen.route) {
            WelcomeScreen(navController)
        }

        composable(route = ScreensNavigation.LoginScreen.route) {
            LoginScreen(navHostController = navController)
        }

        composable(route = ScreensNavigation.RegisterScreen.route) {
            RegisterScreen(navHostController = navController)
        }
        composable(route = ScreensNavigation.BachelorsScreen.route) {
            BachelorsScreen(navHostController = navController,)
        }
    }
}