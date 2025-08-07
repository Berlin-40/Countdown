package com.countdown.presentation.countdown.selectionCountdown

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.countdown.presentation.component.Text.SelectionBanner
import com.countdown.presentation.component.bottombar.MyBottomBarDelete
import com.countdown.presentation.component.cards.CardsCountdownItemSelection
import com.countdown.presentation.component.topbar.TopBarCountdownList
import com.countdown.presentation.countdown.navigation.CountdownRoutes
import com.countdown.presentation.navigation.AppRoutes
import com.countdown.ui.theme.Black

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
                is SelectionAction.Delete -> {
                    navController.navigate(AppRoutes.CountdownRoutes)
                }
                else -> {}
            }
        }
    }
    LaunchedEffect(itemSelected) {
        viewModel.onAction(SelectionAction.isSelect(itemSelected))
    }

    val selectionState by viewModel.selectionState.collectAsState()
    val listOfSelected = selectionState.listOfSelected
    val listOfCountdowns = selectionState.listOfCountdowns
    val selectedCount = listOfSelected.size


    if(selectionState.isLoading){
        TopBarCountdownList("Selection")

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }
    else{
        val text = if (selectedCount == 0) {
            "Sélectionner un élément"
        } else {
            "$selectedCount sélectionné${if (selectedCount > 1) "s" else ""}"
        }

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 72.dp) // laisse la place au bottom bar
            ) {
                TopBarCountdownList(text,
                    onAction = {
                        var show by remember { mutableStateOf(false) }
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                    IconButton(
                                        onClick = {
                                            show = !show
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.MoreVert,
                                            contentDescription = "All selected",
                                        )
                                    }
                                val selectedText = if (selectedCount == 0) {
                                    "Tout sélectionner"
                                } else {
                                    "Annuler la sélection"
                                }
                                DropdownMenu(
                                    expanded = show,
                                    onDismissRequest = { show = false },
                                    offset = DpOffset(x = 0.dp, y = (-8).dp),
                                ) {
                                    SelectionBanner(
                                        text = selectedText,
                                        onSelectAll = {
                                            show = false
                                            viewModel.onAction(SelectionAction.AllSelected)
                                        }
                                    )
                                }
                            }
                    }
                )

                LazyColumn {
                    items(listOfCountdowns) { item ->

                        CardsCountdownItemSelection(
                            id = item.id,
                            title = item.name,
                            duration = item.time,
                            isSelected = listOfSelected.contains(item.id),
                            toggleSelection = { viewModel.onAction(SelectionAction.isSelect(item.id)) }
                        )
                    }
                }
            }

            MyBottomBarDelete(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                viewModel.onAction(SelectionAction.Delete)
            }
        }
    }
}