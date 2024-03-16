package com.example.merlinproject.ui.navigation.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.merlinproject.ui.navigation.graph.HomeDrawerScreen
import com.example.merlinproject.ui.utils.MerlinIcons

data class MenuItems(
    val title: String? = "",
    val selectedIcon: ImageVector = Icons.Filled.Home,
    val unselectedIcon: ImageVector = Icons.Outlined.Home,
    val hasNews: Boolean = false,
    val badgeCount: Int? = null,
    val route: String = ""
) {
    fun bottomNavigationItems(): List<MenuItems> {
        return listOf(
            MenuItems(
                title = "Resumen",
                selectedIcon = MerlinIcons.HomeFilled,
                unselectedIcon = MerlinIcons.HomeOutlined,
                hasNews = false,
                route = HomeDrawerScreen.ResumeScreen.route
            ),
            MenuItems(
                title = "Notas",
                selectedIcon = MerlinIcons.CheckListIconSelected,
                unselectedIcon = MerlinIcons.CheckListIconUnSelected,
                hasNews = false,
                route = HomeDrawerScreen.NotesScreen.route
            ),
            MenuItems(
                title = "Horario",
                selectedIcon = MerlinIcons.TimeIconSelected,
                unselectedIcon = MerlinIcons.TimeIconUnSelected,
                hasNews = false,
                route = HomeDrawerScreen.TimeScreen.route
            ),
            MenuItems(
                title = "Calendario",
                selectedIcon = MerlinIcons.CalendarIconSelected,
                unselectedIcon = MerlinIcons.CalendarIconUnSelected,
                hasNews = false,
                route = HomeDrawerScreen.CalendarScreen.route
            ),
            MenuItems(
                title = "Calificaciones",
                selectedIcon = MerlinIcons.WinnerIconSelected,
                unselectedIcon = MerlinIcons.WinnerIconUnSelected,
                hasNews = false,
                route = HomeDrawerScreen.RatingsScreen.route
            ),
            MenuItems(
                title = "Asignaturas",
                selectedIcon = MerlinIcons.DescriptionIconsSelected,
                unselectedIcon = MerlinIcons.DescriptionIconsUnSelected,
                hasNews = false,
                route = HomeDrawerScreen.SubjectScreen.route
            ),
            MenuItems(
                title = "Profesores",
                selectedIcon = MerlinIcons.TeacherIConsSelected,
                unselectedIcon = MerlinIcons.TeacherIConsUnSelected,
                hasNews = false,
                route = HomeDrawerScreen.TeachersScreen.route
            ),
            MenuItems(
                title = "UACM Campus",
                selectedIcon = MerlinIcons.CampusIconSelected,
                unselectedIcon = MerlinIcons.CampusUnIconSelected,
                hasNews = false,
                route = HomeDrawerScreen.CampusScreen.route
            ),
            MenuItems(
                title = "Ayuda y opinion",
                selectedIcon = MerlinIcons.HelpIconSelected,
                unselectedIcon = MerlinIcons.HelpIconUnSelected,
                hasNews = false,
                route = HomeDrawerScreen.HelperScreen.route
            ),
            MenuItems(
                title = "Configuración",
                selectedIcon = MerlinIcons.settingsIconsSelected,
                unselectedIcon = MerlinIcons.settingsIconsUnSelected,
                hasNews = false,
                route = HomeDrawerScreen.ConfigScreen.route
            )
        )
    }
}
