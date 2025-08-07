package com.countdown.presentation.countdown.selectionCountdown

sealed interface SelectionAction {
    data class isSelect(val id: Int) : SelectionAction
    data object delete : SelectionAction
}