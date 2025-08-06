package com.countdown.domain.model

import java.util.Date

data class Countdown(
    val id: Int = 0,
    val name: String,
    val date: Date,
    val time: String,
)