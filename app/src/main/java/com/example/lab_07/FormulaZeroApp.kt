package com.example.lab_07

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun FormulaZeroApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        // This tell navController what screen you want to go to.
        // So typing in .navigate( "Desired Name" )  will then
        // proceed to run the lamada function. Which takes you to the Ui
        composable("home") { HomeScreen(navController) }
        composable ( "driver") {  DriverScreen(navController) }
        composable("races") { UpcomingRacesScreen(navController) }
    }
}