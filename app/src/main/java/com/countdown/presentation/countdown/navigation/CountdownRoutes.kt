package com.countdown.presentation.countdown.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface CountdownRoutes {
    @Serializable
    data object List: CountdownRoutes
    @Serializable
    data class Selection(val itemSelected: Int): CountdownRoutes
    @Serializable
    data object Add: CountdownRoutes
    @Serializable
    data class Update(val id: Int): CountdownRoutes
    @Serializable
    data class Detail(val id: Int): CountdownRoutes

}