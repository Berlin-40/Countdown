package com.countdown.domain.usecase

import com.countdown.domain.model.Countdown
import com.countdown.domain.repository.Repository
import javax.inject.Inject

class SaveCountdownUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(countdown: Countdown) {
        repository.saveCountdown(countdown)
    }
}
