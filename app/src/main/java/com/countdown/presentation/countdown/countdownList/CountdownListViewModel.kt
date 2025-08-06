package com.countdown.presentation.countdown.countdownList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.countdown.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountdownListViewModel
@Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<CountdownListAction>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _state = mutableStateOf(CountdownListState())
    val state = _state

    fun onItemClick(itemId: Int) {
        viewModelScope.launch {
            _eventFlow.emit(CountdownListAction.NavigateToCountdown(itemId))
        }
    }

    init {
        viewModelScope.launch {
            _state.value = CountdownListState(
                listOfCountdowns = repository.getAllCountdowns()
            )
        }
    }

}