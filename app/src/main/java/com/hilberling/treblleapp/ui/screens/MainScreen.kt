package com.hilberling.treblleapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun MainScreen(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = "signup") {
        composable("signup") {
            SignUp()
        }
    }
}