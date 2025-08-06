package com.countdown.presentation.countdown.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.countdown.presentation.countdown.countdownList.CountdownListUi
import com.countdown.presentation.navigation.AppRoutes


fun NavGraphBuilder.CountdownNav(navController: NavController) {
    navigation<AppRoutes.Countdown>(startDestination = CountdownRoutes.List ) {

        composable<CountdownRoutes.List>{
            CountdownListUi(navController = navController)
        }
        composable<CountdownRoutes.Add>{

        }
        composable<CountdownRoutes.Update>{

        }
        composable<CountdownRoutes.Detail>{
            val id = it.toRoute<CountdownRoutes.Detail>()

        }
    }
}

fun NavHostController.navigate(route: AppRoutes.Countdown) {
    navigate(AppRoutes.Countdown)
}