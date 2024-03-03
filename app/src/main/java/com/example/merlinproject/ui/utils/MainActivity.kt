package com.example.merlinproject.ui.utils

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.merlinproject.ui.navigation.composable.NavigationMD3
import com.example.merlinproject.ui.navigation.graph.RootNavGraph
import com.example.merlinproject.ui.theme.MerlinProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MerlinProjectTheme {
                    navController = rememberNavController()
                    RootNavGraph(navController = navController)
            }
        }
    }
}
