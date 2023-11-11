package com.example.merlinproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.merlinproject.data.Resource
import com.example.merlinproject.ui.LoginViewModel
import com.example.merlinproject.ui.theme.MerlinProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MerlinProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var email by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }
                    val loginFlow = loginViewModel.loginFlow.collectAsState()
                    loginFlow.value?.let {
                        when (it) {
                            is Resource.Failure -> {
                                Toast.makeText(LocalContext.current, "Error", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            Resource.Loading -> {
                                CircularProgressIndicator()
                            }

                            is Resource.Success -> {
                                Toast.makeText(LocalContext.current, "Exito", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            else -> {}
                        }
                        Button(onClick = {  loginViewModel.login("", "") }) {

                        }
                    }
                    Greeting("Android", mviewmodel = loginViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, mviewmodel: ViewModel) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MerlinProjectTheme {

    }
}