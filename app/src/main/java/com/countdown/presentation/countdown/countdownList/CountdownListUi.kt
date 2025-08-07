package com.countdown.presentation.countdown.countdownList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.countdown.domain.model.Countdown
import com.countdown.presentation.component.cards.CardsCountdownItem
import com.countdown.presentation.component.topbar.TopBarCountdownList
import com.countdown.presentation.countdown.navigation.CountdownRoutes
import com.countdown.presentation.navigation.AppRoutes
import com.countdown.ui.theme.Black

@Composable
fun CountdownListUi(
    viewModel: CountdownListViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
){
    val listOfCountdowns = viewModel.state.value.listOfCountdowns

    // Observer les événements
    LaunchedEffect(true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is CountdownListAction.NavigateToCountdownMinuteur -> {
                    navController.navigate(AppRoutes.Minuteur)
                }
                is CountdownListAction.NavigateToCountdownDetail -> {
                    navController.navigate(CountdownRoutes.Detail(event.countdownId))
                }
                is CountdownListAction.GotoSelection -> {
                    navController.navigate(CountdownRoutes.Selection(0))
                }

                else ->{}
            }
        }
    }
    LazyColumn(
        modifier = modifier
    ) {
        item{
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
            })
        }
        items(listOfCountdowns) {it->

            CardsCountdownItem(
                title = it.name,
                duration = it.date.toString(),
                onItemClick = {viewModel.onAction(CountdownListAction.NavigateToCountdownDetail(it.id))},
                onLongClick = {viewModel.onAction(CountdownListAction.GotoSelection)}
            )
        }
    }
}