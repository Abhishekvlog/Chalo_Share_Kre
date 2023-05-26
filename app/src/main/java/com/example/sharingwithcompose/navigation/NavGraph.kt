package com.example.sharingwithcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sharingwithcompose.view.fragments.RegistrationScreen
import com.example.sharingwithcompose.view.fragments.StorageScreen
import com.example.sharingwithcompose.view.fragments.TypeScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()){
    NavHost(navController = navController, startDestination = Screens.registrationScren.route){
        composable(route = Screens.registrationScren.route){
            RegistrationScreen(navController)
        }
        composable(route = Screens.typeScreen.route){
            TypeScreen(navController)
        }
        composable(route = Screens.storageScreen.route){
            StorageScreen()
        }
    }
}