package com.hilberling.treblleapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.hilberling.treblleapp.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(navHostController: NavHostController, viewModel: MainViewModel) {

    NavHost(navController = navHostController, startDestination = "signup") {
        composable("signup") {
            SignUp(viewModel)
        }
    }
}