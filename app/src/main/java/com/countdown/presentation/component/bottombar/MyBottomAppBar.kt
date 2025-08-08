package com.countdown.presentation.component.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.countdown.presentation.navigation.AppRoutes

@Composable
fun MyBottomBar(
    navController: NavController,
    currentRoute: String ,
    modifier: Modifier = Modifier
){
    val destinations = listOf("Countdown", "Minuteur")
    var currentRoute by remember { mutableStateOf(
        if(currentRoute == "Minuteur"){
            "Minuteur"
        }
        else{
            "Countdown"
        }
    )
    }
    fun onNavigateToDestination(destination: String) {
        currentRoute =  destination
    }

    BottomBarNav(
        destinations = destinations,
        onNavigateToDestination = {
            onNavigateToDestination(it)

            when(it){
                "Countdown" -> {
                    navController.popBackStack()
                    navController.navigate(AppRoutes.CountdownRoutes)
                }
                "Minuteur" -> {
                    navController.popBackStack()
                    navController.navigate(AppRoutes.Minuteur)
                }

            }
        },
        currentRoute = currentRoute
    )
}