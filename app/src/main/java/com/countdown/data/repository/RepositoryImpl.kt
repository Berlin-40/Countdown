package com.countdown.data.repository

import com.countdown.data.dao.CountdownDao
import com.countdown.data.mappers.toCountdown
import com.countdown.data.mappers.toCountdownEntity
import com.countdown.domain.model.Countdown
import com.countdown.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val countdownDao: CountdownDao
): Repository  {

    override suspend fun saveCountdown(countdown: Countdown) {
        countdownDao.insert(countdown.toCountdownEntity())
    }

    override suspend fun getAllCountdowns(): List<Countdown> {
        return countdownDao.getAll().map { it.toCountdown() }
    }

    override suspend fun deleteCountdown(countdown: Countdown) {
        countdownDao.delete(countdown.toCountdownEntity())
    }
    override suspend fun updateCountdown(countdown: Countdown) {
        countdownDao.update(countdown.toCountdownEntity())
    }
}