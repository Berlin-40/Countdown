package com.countdown.presentation.minuteur

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MinuteurViewModel @Inject constructor(

): ViewModel()
{
    var minuteurState by mutableStateOf(MinuteurState())
        private set
}