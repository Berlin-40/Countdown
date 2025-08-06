package com.countdown.data.repository

import com.countdown.data.dao.CountdownDao
import com.countdown.domain.model.Countdown
import com.countdown.domain.repository.Repository
import java.util.Date
import javax.inject.Inject


class FakeRepositoryImpl @Inject constructor(): Repository{

    override suspend fun saveCountdown(countdown: Countdown) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCountdowns(): List<Countdown> {
        return List(4) { Countdown(
            id = 1,
            name = "Birthday",
            date = Date(),
            time = "10:00"
        ) }
    }

    override suspend fun deleteCountdown(countdown: Countdown) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCountdown(countdown: Countdown) {
        TODO("Not yet implemented")
    }

}