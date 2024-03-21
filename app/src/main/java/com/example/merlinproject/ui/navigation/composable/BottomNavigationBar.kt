package com.example.merlinproject.ui.navigation.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier
) {
    var selectedItemIndex by remember { mutableStateOf(0) }
    val navController = rememberNavController()
    val bottomNavigationItems = remember { MenuItems().bottomNavigationItems() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            val currentNavItem = bottomNavigationItems.find { it.route == currentRoute }
            currentNavItem?.let { navigationItem ->
                val titleResourceId = navigationItem.title
            }
        },
        bottomBar = {
            NavigationBar {
                MenuItems().bottomNavigationItems()
                    .forEachIndexed { index, navigationItem ->
                        NavigationBarItem(
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            label = {
                                Text(
                                    text = "", maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            alwaysShowLabel = false,
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (navigationItem.badgeCount != null) {
                                            Badge {
                                                Text(text = navigationItem.badgeCount.toString())
                                            }
                                        } else if (navigationItem.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            navigationItem.selectedIcon
                                        } else navigationItem.unselectedIcon,
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
            }
        }
    ) { paddingValues ->
        //  AppNavigation(
        //    modifier = Modifier.padding(paddingValues = paddingValues),
        //  navController
        //)

    }
}
