package com.countdown.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoutes {
    @Serializable
    data  object CountdownRoutes: AppRoutes

    @Serializable
    data  object Minuteur: AppRoutes
}