package com.countdown.presentation.countdown.selectionCountdown

sealed interface SelectionAction {
    data class isSelect(val id: Int) : SelectionAction
    object Delete : SelectionAction
    object AllSelected : SelectionAction
}