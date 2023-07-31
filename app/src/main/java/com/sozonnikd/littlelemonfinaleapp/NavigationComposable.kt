package com.sozonnikd.littlelemonfinaleapp

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigation(navController: NavHostController){
    val context = LocalContext.current
    val startDestination = checkIfLogin(context)

    NavHost(navController = navController, startDestination = startDestination){
        composable(Home.route){
            Home(navController)
        }
        composable(Profile.route){
            Profile(navController)
        }
        composable(Onboarding.route){
            Onboarding(navController)
        }
    }
}

fun checkIfLogin(context: Context): String{
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    return if (sharedPreferences.getString("firstName", "") == ""){
        Onboarding.route
    } else {
        Home.route
    }
}
