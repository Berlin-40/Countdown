package com.countdown.domain.repository

import com.countdown.domain.model.Countdown

interface Repository {
    suspend fun saveCountdown(countdown: Countdown)

    suspend fun getAllCountdowns(): List<Countdown>

    suspend fun deleteCountdown(countdown: Countdown)

    suspend fun updateCountdown(countdown: Countdown)
}