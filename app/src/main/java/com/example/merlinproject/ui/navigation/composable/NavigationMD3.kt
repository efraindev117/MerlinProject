package com.example.merlinproject.ui.navigation.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.merlinproject.R
import com.example.merlinproject.ui.features.home.HomeScreen
import com.example.merlinproject.ui.features.resume.ResumeScreen
import com.example.merlinproject.ui.navigation.graph.HomeDrawerNavGraph
import com.example.merlinproject.ui.navigation.graph.HomeDrawerScreen
import com.example.merlinproject.ui.utils.MerlinIcons.MenuIconMD3
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationMD3(navController: NavHostController) {
    Surface(
        modifier = Modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        val bottomNavigationItems = remember { NavigationItems().bottomNavigationItems() }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: ""
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet(modifier = Modifier.requiredWidth(300.dp)) {
                    Text(
                        text = stringResource(id = R.string.title_navigation_drawer),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(16.dp)
                    )
                    Divider(
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    NavigationItems().bottomNavigationItems()
                        .forEachIndexed { index, items ->
                            NavigationDrawerItem(
                                label = { Text(text = items.title!!) },
                                selected = index == selectedItemIndex,
                                onClick = {
                                    selectedItemIndex = index
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    navController.navigate(items.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            items.selectedIcon
                                        } else {
                                            items.unselectedIcon
                                        },
                                        contentDescription = null
                                    )
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                        }
                }
            }, drawerState = drawerState
        ) {
            Scaffold(topBar = {
                val currentNavItem = bottomNavigationItems.find { it.route == currentRoute }
                currentNavItem?.let { navigationItem ->
                    val titleResourceId = navigationItem.title
                    TopAppBar(
                        title = {
                            Text(text = titleResourceId.toString())
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(imageVector = MenuIconMD3, contentDescription = null)
                            }
                        }
                    )
                }
            }
            ) { paddingValues ->
                HomeDrawerNavGraph(navHostController = navController,paddingValues)
            }
        }
    }
}