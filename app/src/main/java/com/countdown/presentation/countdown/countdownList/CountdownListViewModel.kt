package com.countdown.presentation.countdown.countdownList

import androidx.lifecycle.ViewModel
import com.countdown.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountdownListViewModel
@Inject constructor(
    val repository: Repository
) : ViewModel() {


}