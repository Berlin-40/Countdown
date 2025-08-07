package com.countdown.data.repository

import com.countdown.data.dao.CountdownDao
import com.countdown.domain.model.Countdown
import com.countdown.domain.repository.Repository
import java.util.Date
import javax.inject.Inject


class FakeRepositoryImpl @Inject constructor(): Repository{

    val listOfCountdowns = mutableListOf(
        Countdown(id = 1, name = "Birthday", date = Date(), time = "10:00"),
        Countdown(id = 2, name = "Meeting", date = Date(), time = "11:00"),
        Countdown(id = 3, name = "Event", date = Date(), time = "12:00"),
        Countdown(id = 4, name = "Holiday", date = Date(), time = "13:00"),
    )

    override suspend fun saveCountdown(countdown: Countdown) {
        listOfCountdowns.removeIf { it.id == countdown.id }
        listOfCountdowns.add(countdown)
    }

    override suspend fun getAllCountdowns(): List<Countdown> {
        return listOfCountdowns
    }

    override suspend fun deleteCountdown(countdown: Countdown) {
        listOfCountdowns.remove(countdown)
    }

    override suspend fun updateCountdown(countdown: Countdown) {
        val index = listOfCountdowns.indexOfFirst { it.id == countdown.id }
        if (index != -1) {
            listOfCountdowns[index] = countdown
        } else {
            // Tu peux aussi décider d'ajouter s'il n'existe pas encore
            listOfCountdowns.add(countdown)
        }
    }

}