package com.example.merlinproject.ui.utils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.merlinproject.ui.features.auth.login.LoginScreen
import com.example.merlinproject.ui.features.bachelors.BachelorsScreen
import com.example.merlinproject.ui.features.maps.MapsScreen
import com.example.merlinproject.ui.navigation.NestedNavigation
import com.example.merlinproject.ui.theme.MerlinProjectTheme
import com.google.common.collect.Maps
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MerlinProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    //NestedNavigation(navController = navController)
                    //RegisterScreen(navHostController = navController )
                    LoginScreen(navHostController = navController)
                    //BachelorsScreen(navHostController = navController)
                    MapsScreen(navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MerlinProjectTheme {

    }
}