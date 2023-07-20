package com.example.calculator.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun CalculatorApp() {
    val naviCtrl = rememberNavController()

    NavHost(navController = naviCtrl, startDestination = "main") {
        composable(route = "main") {
            MainScreen()
        }
    }


}