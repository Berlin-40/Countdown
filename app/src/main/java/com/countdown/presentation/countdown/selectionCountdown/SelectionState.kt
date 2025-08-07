package com.countdown.presentation.countdown.selectionCountdown

import com.countdown.domain.model.Countdown


data class SelectionState(
    val listOfSelected: List<Int> = emptyList(),
    val listOfCountdowns: List<Countdown> = emptyList(),
    val isLoading: Boolean = false

)