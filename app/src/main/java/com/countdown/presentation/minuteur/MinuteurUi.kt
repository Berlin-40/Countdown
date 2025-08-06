package com.countdown.presentation.minuteur

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.countdown.presentation.component.animation.MarmiteAnimation
import com.countdown.presentation.component.box.BoxDuration
import com.countdown.presentation.countdown.countdownList.CountdownListViewModel

@Composable
fun MinuteurUi(
    viewModel: MinuteurViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val minuteurState = viewModel.minuteurState

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        BoxDuration(
            modifier = Modifier
        )
        MarmiteAnimation(duration = 2)

    }
}