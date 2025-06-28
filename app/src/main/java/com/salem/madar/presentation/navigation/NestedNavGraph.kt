package com.salem.madar.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NestedNavGraph(){
    val navController = rememberNavController()
    NavHost( navController = navController  , startDestination = AddUserScreen ) {
        mainGraph(navController)
    }
}
