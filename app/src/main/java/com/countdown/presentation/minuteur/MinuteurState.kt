package com.countdown.presentation.minuteur

data class MinuteurState(
    val minute: Int = 0,
    val heure: Int = 0,
    val stoped: Boolean = true,
    val started: Boolean = false,

)