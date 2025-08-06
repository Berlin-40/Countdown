package com.countdown.presentation.countdown.countdownList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.countdown.presentation.component.cards.CardsCountdownItem

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
                is CountdownListAction.NavigateToCountdown -> {
                    navController.navigate("detail/${event}")
                }
                else ->{}
            }
        }
    }
    LazyColumn(
        modifier = modifier
    ) {
        items(listOfCountdowns) {it->

            CardsCountdownItem(
                title = it.name,
                duration = it.date.toString(),
                onItemClick = {}
            )
        }
    }
}