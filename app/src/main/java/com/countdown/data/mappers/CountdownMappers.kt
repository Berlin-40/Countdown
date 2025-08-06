package com.countdown.data.mappers

import com.countdown.data.entity.CountdownEntity
import com.countdown.domain.model.Countdown

fun Countdown.toCountdownEntity() = CountdownEntity(id, name, date, time)

fun CountdownEntity.toCountdown() = Countdown(id, name, date, time)