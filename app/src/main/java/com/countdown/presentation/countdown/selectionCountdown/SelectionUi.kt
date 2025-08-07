package com.countdown.presentation.countdown.selectionCountdown

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.countdown.presentation.component.cards.CardsCountdownItemSelection
import com.countdown.presentation.navigation.AppRoutes

@Composable
fun SelectionUi(
    navController: NavController,
    itemSelected: Int,
    viewModel: SelectionViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
){
    LaunchedEffect(true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is SelectionAction.isSelect -> {

                }
                is SelectionAction.delete -> {
                    navController.navigate(AppRoutes.CountdownRoutes)
                }
                else -> {}
            }
        }
    }
    val selectionState by viewModel.selectionState.collectAsState()
    val listOfSelected = selectionState.listOfSelected
    val listOfCountdowns = selectionState.listOfCountdowns

     LazyColumn {
         items(listOfCountdowns) { item ->

             CardsCountdownItemSelection(
                    id = item.id,
                    title = item.name,
                    duration = item.time,
                    isSelected = listOfSelected.contains(item.id),
                    toggleSelection = {viewModel.onAction(SelectionAction.isSelect(item.id))}
             )
         }
     }
}