package com.sozonnikd.littlelemonfinaleapp

import android.content.Context
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sozonnikd.littlelemonfinaleapp.ui.theme.LittleLemonFinaleAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonFinaleAppTheme {
                val navController = rememberNavController()

                MyNavigation(navController)
            }
        }
    }
}

@Composable
fun Activity(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Onboarding.route){
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
 //   MyNavigation()
}