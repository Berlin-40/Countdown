package com.countdown.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.countdown.presentation.countdown.navigation.CountdownNav
import com.countdown.presentation.minuteur.MinuteurUi


@Composable
fun NavApp(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppRoutes.CountdownRoutes
    ) {

        CountdownNav(navController = navController)

        composable<AppRoutes.Minuteur>{
            MinuteurUi(navController = navController)
        }
    }
}