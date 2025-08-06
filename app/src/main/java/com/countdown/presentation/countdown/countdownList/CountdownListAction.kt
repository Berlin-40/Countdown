package com.countdown.presentation.countdown.countdownList

sealed interface CountdownListAction {
    data class NavigateToCountdownDetail(val countdownId: Int) : CountdownListAction
    object NavigateToCountdownMinuteur : CountdownListAction

}