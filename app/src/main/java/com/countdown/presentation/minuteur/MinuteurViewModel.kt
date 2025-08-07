package com.countdown.presentation.minuteur

import android.util.Log
import android.util.Log.d
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MinuteurViewModel @Inject constructor() :ViewModel() {

    private val _minuteurState = MutableStateFlow(MinuteurState())
    val minuteurState = _minuteurState.asStateFlow()

    fun onAction(action: MinuteurAction) {
        Log.d("MinuteurViewModel", "onAction: $action")
        when (action) {
            is MinuteurAction.Start -> {
                if (minuteurState.value.minute <= 0 && minuteurState.value.seconde <= 0) {
                    _minuteurState.update {
                        it.copy(errorMessage = "La durée doit être supérieure à 0 minute.")
                    }
                } else {
                    _minuteurState.update {
                        it.copy(launched = true, started = true, errorMessage = null)
                    }
                }
            }

            is MinuteurAction.Stop -> {
                _minuteurState.update { it.copy(started = false, infoMessage = "Minuteur arrêté.") }

            }

            is MinuteurAction.SetSeconde -> {

                _minuteurState.update { it.copy(seconde = action.seconde) }

            }

            is MinuteurAction.SetMinute -> {
                _minuteurState.update { it.copy(minute = action.minute) }
            }

            is MinuteurAction.Reset -> {
                _minuteurState.update {
                    it.copy(
                        minute = 0,
                        seconde = 0,
                        started = false,
                        launched = false,
                        finished = false
                    )
                }
            }
        }
    }

    fun clearMessages() {
        _minuteurState.update {
            it.copy(errorMessage = null, infoMessage = null)
        }
    }

    fun finish(){
        _minuteurState.update {
            it.copy(finished = true, started = false, launched = false)
        }
    }

    suspend fun run(){
            while (minuteurState.value.started && (minuteurState.value.minute > 0 || minuteurState.value.seconde > 0)) {
                delay(1000L)
                decrementSeconds()
            }
            if (minuteurState.value.seconde == 0 && minuteurState.value.minute == 0) {
                finish()
            }
    }
    fun decrementSeconds() {
        _minuteurState.update { currentState ->
            when {
                currentState.seconde > 0 -> currentState.copy(seconde = currentState.seconde - 1,infoMessage = "${currentState.seconde - 1} secondes restantes")
                currentState.minute > 0 && currentState.seconde == 0 -> currentState.copy(minute = currentState.minute - 1, seconde = 59,infoMessage = "${currentState.minute - 1} minutes restantes")
                else -> currentState
            }
        }
    }
}