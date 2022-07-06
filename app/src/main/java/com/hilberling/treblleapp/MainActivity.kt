package com.hilberling.treblleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.hilberling.treblleapp.data.Api
import com.hilberling.treblleapp.data.Repository
import com.hilberling.treblleapp.ui.screens.MainScreen
import com.hilberling.treblleapp.ui.screens.SignUp
import com.hilberling.treblleapp.ui.theme.TreblleAppTheme
import com.hilberling.treblleapp.ui.viewmodel.MainViewModel
import com.hilberling.treblleapp.ui.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {

    private val repository by lazy {
        Repository(Api.authService)
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(repository = repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TreblleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Authentication(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun Authentication(viewModel: MainViewModel) {
    val navController = rememberNavController()
    MainScreen(navHostController = navController, viewModel = viewModel)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TreblleAppTheme {
        Authentication(viewModel())
    }
}