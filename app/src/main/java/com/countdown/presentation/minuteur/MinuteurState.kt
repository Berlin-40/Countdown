package com.countdown.presentation.minuteur

data class MinuteurState(
    val seconde: Int = 0,
    val minute: Int = 0,
    val started: Boolean = false,
    val finished: Boolean = false,
    val launched: Boolean = false,
    val errorMessage: String? = null,
    val infoMessage: String? = null
)