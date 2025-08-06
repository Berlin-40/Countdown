package com.countdown.domain.usecase

import com.countdown.domain.model.Countdown
import com.countdown.domain.repository.Repository
import javax.inject.Inject

class DeleteCountdownUseCase @Inject constructor(
    val countdown: Countdown,
    private val repository: Repository
){
    suspend operator fun invoke(countdown: Countdown) = repository.deleteCountdown(countdown)

}