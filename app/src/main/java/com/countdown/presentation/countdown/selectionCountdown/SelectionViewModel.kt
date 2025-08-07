package com.countdown.presentation.countdown.selectionCountdown

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.launch
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
                viewModelScope.launch {
                    performDelete()
                    _eventFlow.emit(CountdownListAction.GotoSelection)
                }
            }
        }
    }
    init {
        loadCountdown()
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
       viewModelScope.launch {
           _state.value.listOfSelected.forEach {id ->
               val countdown = _state.value.listOfCountdowns.find { it.id == id }
               if (countdown != null) {
                   repository.deleteCountdown(countdown)
               }
           }
           _eventFlow.emit(SelectionAction.delete)

       }
        _state.update { current ->
            current.copy(listOfSelected = emptyList())
        }
        loadCountdown()
    }

    private fun loadCountdown() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                listOfCountdowns = repository.getAllCountdowns(),
                isLoading = true)
        }
    }

}