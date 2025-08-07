package com.countdown

import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.countdown.presentation.component.animation.BubbleAnimation
import com.countdown.presentation.component.bottombar.BottomBarNav
import com.countdown.presentation.component.bottombar.BottombarPreview
import com.countdown.presentation.component.bottombar.MyBottomBar
import com.countdown.presentation.component.scrollList.CircularScrollList
import com.countdown.presentation.component.topbar.TopBarCountdownList
import com.countdown.presentation.component.topbar.TopBarCountdownListWithActionPreview
import com.countdown.presentation.countdown.countdownList.CountdownListUi
import com.countdown.presentation.countdown.navigation.CountdownRoutes
import com.countdown.presentation.minuteur.MinuteurUi
import com.countdown.presentation.navigation.AppRoutes
import com.countdown.presentation.navigation.NavApp
import com.countdown.ui.theme.Black
import com.countdown.ui.theme.CountdownTheme
import com.countdown.ui.theme.RedPrimary
import com.countdown.ui.theme.RedSecondary
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentRoute = navController.currentBackStackEntryAsState().value
            val destination = currentRoute?.destination?.route
            CountdownTheme {
                Scaffold(
                    topBar = {
                        TopBarCountdownListWithActionPreview()
                        when {
                            destination?.contains(CountdownRoutes.List.toString()) == true ->{
                                TopBarCountdownList("Countdown",onAction = {
                                    IconButton(
                                        onClick = {navController.navigate(CountdownRoutes.Add) }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "Add",
                                            tint = Black
                                        )
                                    }
                                }
                                )
                            }
                            destination?.contains(AppRoutes.Minuteur.toString()) == true -> TopBarCountdownList("Minuteur")
                            destination?.contains(CountdownRoutes.Add.toString()) == true -> {
                                TopBarCountdownList(
                                    title = "Add",
                                    onNavigation = {
                                        IconButton(
                                            onClick = { navController.popBackStack() }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.KeyboardArrowLeft,
                                                contentDescription = "Back",
                                                tint = Black,
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }
                                    }
                                )
                            }
                            destination?.contains(CountdownRoutes.Update.toString()) == true -> {}
                            destination?.contains(CountdownRoutes.Detail.toString()) == true -> {

                            }
                            else->{}
                        }
                    },

                    bottomBar = {
                        when {
                            destination?.contains(CountdownRoutes.List.toString()) == true -> MyBottomBar(navController,"List")
                            destination?.contains(AppRoutes.Minuteur.toString()) == true -> MyBottomBar(navController, "Minuteur")
                            destination?.contains(CountdownRoutes.Add.toString()) == true -> {}
                            destination?.contains(CountdownRoutes.Update.toString()) == true -> {}
                            destination?.contains(CountdownRoutes.Detail.toString()) == true -> {}
                            else->{}
                        }
                                },
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    containerColor = RedSecondary

                ) { innerPadding ->
                    NavApp(modifier = Modifier.padding(innerPadding), navController = navController)
                }
            }
        }
    }
}
