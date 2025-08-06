package com.countdown.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoutes {
    @Serializable
    object Countdown: AppRoutes

    @Serializable
    object Minuteur: AppRoutes
}