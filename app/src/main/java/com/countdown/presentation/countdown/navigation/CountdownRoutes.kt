package com.countdown.presentation.countdown.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface CountdownRoutes {
    @Serializable
    object List: CountdownRoutes
    @Serializable
    object Add: CountdownRoutes
    @Serializable
    data class Update(val id: Int): CountdownRoutes
    @Serializable
    data class Detail(val id: Int): CountdownRoutes

}