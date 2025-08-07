package com.countdown.presentation.countdown.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.countdown.presentation.countdown.countdownList.CountdownListUi
import com.countdown.presentation.countdown.selectionCountdown.SelectionUi
import com.countdown.presentation.navigation.AppRoutes


fun NavGraphBuilder.CountdownNav(navController: NavController) {
    navigation<AppRoutes.CountdownRoutes>(startDestination = CountdownRoutes.List ) {

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
        composable<CountdownRoutes.Selection>{
            val args = it.toRoute<CountdownRoutes.Selection>()
            SelectionUi(navController = navController, itemSelected = args.itemSelected)
        }
    }
}

fun NavHostController.navigate(route: AppRoutes.CountdownRoutes) {
    navigate(AppRoutes.CountdownRoutes)
}