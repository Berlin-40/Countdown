package com.countdown.presentation.countdown.countdownList

interface CountdownListAction {
    data class NavigateToCountdown(val countdownId: Int) : CountdownListAction

}