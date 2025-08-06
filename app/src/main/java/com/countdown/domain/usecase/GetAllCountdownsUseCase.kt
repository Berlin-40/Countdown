package com.countdown.domain.usecase

import com.countdown.domain.repository.Repository
import javax.inject.Inject

class GetAllCountdownsUseCase @Inject constructor(
    val repository: Repository
){
    suspend operator fun invoke() = repository.getAllCountdowns()
}