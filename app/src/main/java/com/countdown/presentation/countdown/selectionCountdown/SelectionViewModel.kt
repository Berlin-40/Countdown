package com.countdown.presentation.countdown.selectionCountdown

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.countdown.domain.repository.Repository
import com.countdown.presentation.countdown.countdownList.CountdownListAction
import com.countdown.presentation.countdown.countdownList.CountdownListState
import com.countdown.presentation.minuteur.MinuteurState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SelectionViewModel@Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<CountdownListAction>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _state = MutableStateFlow(SelectionState())
    val selectionState = _state.asStateFlow()

    fun onAction(action: SelectionAction){
        when(action){
            is SelectionAction.isSelect ->{
                performSelected(action.id)
            }
            is SelectionAction.delete ->{
                performDelete()
            }
        }
    }
    private fun performSelected(id: Int){
        _state.update { current ->
            val updatedList = if (id in current.listOfSelected) {
                current.listOfSelected - id
            } else {
                current.listOfSelected + id
            }
            current.copy(listOfSelected = updatedList)
        }
    }
    private fun performDelete(){
        _state.update { current ->
            current.copy(listOfSelected = emptyList())
        }
    }

}