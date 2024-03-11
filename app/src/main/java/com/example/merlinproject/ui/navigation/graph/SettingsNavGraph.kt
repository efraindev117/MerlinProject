package com.example.merlinproject.ui.navigation.graph




sealed class SettingsMenuScreen(val route: String) {
    object UserScreen : HomeDrawerScreen(route = "user_screen")

}