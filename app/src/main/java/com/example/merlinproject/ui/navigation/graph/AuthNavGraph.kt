package com.example.merlinproject.ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.merlinproject.ui.features.auth.login.LoginScreen
import com.example.merlinproject.ui.features.auth.register.RegisterScreen
import com.example.merlinproject.ui.features.auth.welcome.WelcomeScreen
import com.example.merlinproject.ui.navigation.RoutesLoginAndRegister
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.LoginScreen.route
    ) {
        composable(route = AuthScreen.LoginScreen.route) {
            LoginScreen(navHostController = navController)
        }

        composable(route = AuthScreen.WelcomeScreen.route) {
            WelcomeScreen(navController)
        }

        composable(route = AuthScreen.RegisterScreen.route) {
            RegisterScreen(navHostController = navController)
        }
    }
}
sealed class AuthScreen(val route: String) {
    object SplashScreen : AuthScreen(RoutesNavigationDrawer.SPLASH_SCREEN_ROUTE_NAME)
    object WelcomeScreen : AuthScreen(RoutesLoginAndRegister.WELCOME_SCREEN_NAVIGATION_ROUTE_NAME)
    object OnboardingScreen : AuthScreen(RoutesNavigationDrawer.ONBOARDING_NAVIGATION_ROUTE_NAME)
    object LoginScreen : AuthScreen(RoutesNavigationDrawer.LOGIN_NAVIGATION_ROUTE_NAME)
    object RegisterScreen : AuthScreen(RoutesNavigationDrawer.REGISTER_NAVIGATION_ROUTE_NAME)
    object ForgetPasswordScreen : AuthScreen(RoutesNavigationDrawer.FORGET_PASSWORD_NAVIGATION_ROUTE_NAME)
}
