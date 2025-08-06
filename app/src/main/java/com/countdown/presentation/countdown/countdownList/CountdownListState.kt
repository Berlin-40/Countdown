package com.countdown.presentation.countdown.countdownList

import com.countdown.domain.model.Countdown

data class CountdownListState(
    val listOfCountdowns: List<Countdown> = emptyList(),
)