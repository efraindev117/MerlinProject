package com.example.merlinproject.ui.navigation.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.merlinproject.ui.navigation.graph.SettingsMenuScreen
import com.example.merlinproject.ui.theme.MerlinProjectIcons

data class SettingsMenu(
    val title: String? = "",
    val selectedIcon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {
    fun settingsNavigationItems(): List<SettingsMenu> {
        return listOf(
            SettingsMenu(
                title = "Cuenta",
                selectedIcon = MerlinProjectIcons.UserSettingsIcon,
                route = SettingsMenuScreen.UserScreen.route
            ),
            SettingsMenu(
                title = "Notificaciones",
                selectedIcon = MerlinProjectIcons.NotificationsSettingsIcon,
                route = SettingsMenuScreen.UserScreen.route
            ),

            SettingsMenu(
                title = "Diversidad",
                selectedIcon = MerlinProjectIcons.DiversitySettingsIcon,
                route = SettingsMenuScreen.UserScreen.route
            ),

            SettingsMenu(
                title = "Apariencia",
                selectedIcon = MerlinProjectIcons.ThemeSettingsIcon,
                route = SettingsMenuScreen.UserScreen.route
            ),
            SettingsMenu(
                title = "Escribe una reseña",
                selectedIcon = MerlinProjectIcons.RateSettingsIcon,
                route = SettingsMenuScreen.UserScreen.route
            ),
            SettingsMenu(
                title = "Traducir",
                selectedIcon = MerlinProjectIcons.LanguageSettingsIcon,
                route = SettingsMenuScreen.UserScreen.route
            ),
            SettingsMenu(
                title = "Conviertete en colaborador",
                selectedIcon = MerlinProjectIcons.GroupSettingsIcon,
                route = SettingsMenuScreen.UserScreen.route
            ),
            SettingsMenu(
                title = "Tecnologías",
                selectedIcon = MerlinProjectIcons.TechSettingsIcon,
                route = SettingsMenuScreen.UserScreen.route
            ),
            SettingsMenu(
                title = "Acerca de",
                selectedIcon = MerlinProjectIcons.InfoSettingsIcon,
                route = SettingsMenuScreen.UserScreen.route
            )
        )
    }
}

