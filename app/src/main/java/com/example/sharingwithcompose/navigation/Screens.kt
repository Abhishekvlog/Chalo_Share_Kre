package com.example.sharingwithcompose.navigation

sealed class Screens(val route : String){
    object registrationScren : Screens(route = "registrationScreen")
    object typeScreen : Screens(route = "typeScreen")
    object storageScreen : Screens(route = "storageScreen")
}
