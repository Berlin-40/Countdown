package com.countdown.presentation.countdown.addCountdown

import androidx.lifecycle.ViewModel
import com.countdown.domain.repository.Repository
import com.countdown.domain.usecase.SaveCountdownUseCase
import com.countdown.presentation.countdown.selectionCountdown.SelectionAction
import com.countdown.presentation.countdown.selectionCountdown.SelectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddCountdownViewModel @Inject constructor(
    val saveCountdownUseCase: SaveCountdownUseCase
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<SelectionAction>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _state = MutableStateFlow(SelectionState())
    val selectionState = _state.asStateFlow()

    fun onAction(action: SelectionAction) {

    }
}