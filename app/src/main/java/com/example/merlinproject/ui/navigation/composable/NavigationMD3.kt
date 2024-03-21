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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.merlinproject.R
import com.example.merlinproject.ui.navigation.graph.HomeDrawerNavGraph
import com.example.merlinproject.ui.theme.lexendFontFamily
import com.example.merlinproject.ui.utils.MerlinIcons.MenuIconMD3
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationMD3(navController: NavHostController) {
    Surface(
        modifier = Modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        val bottomNavigationItems = remember { MenuItems().bottomNavigationItems() }
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
                        fontFamily = lexendFontFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(start = 32.dp, top = 16.dp, bottom = 16.dp)
                    )
                    Divider(
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    MenuItems().bottomNavigationItems()
                        .forEachIndexed { index, items ->
                            NavigationDrawerItem(
                                label = {
                                    Text(
                                        text = items.title!!,
                                        fontFamily = lexendFontFamily,
                                        fontWeight = FontWeight.Normal,
                                    )
                                },
                                selected = index == selectedItemIndex,
                                onClick = {
                                    selectedItemIndex = index
                                    navController.navigate(items.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                    }
                                    scope.launch {
                                        drawerState.close()
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
                            Text(
                                text = titleResourceId.toString(), fontFamily = lexendFontFamily,
                                fontWeight = FontWeight.Bold,
                            )
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
                HomeDrawerNavGraph(navHostController = navController, paddingValues)
            }
        }
    }
}