package com.countdown.presentation.minuteur

interface MinuteurAction {
    object Start : MinuteurAction
    object Stop : MinuteurAction
    object Reset : MinuteurAction
    data class SetMinute(val minute: Int) : MinuteurAction
    data class SetSeconde(val seconde: Int) : MinuteurAction
}