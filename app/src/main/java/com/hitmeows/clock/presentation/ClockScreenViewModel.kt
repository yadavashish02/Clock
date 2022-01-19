package com.hitmeows.clock.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject


@HiltViewModel
class ClockScreenViewModel @Inject constructor(
    private val localTime: LocalTime
) : ViewModel(){
    private val _state = mutableStateOf(ClockState(localTime.hour,localTime.minute,localTime.second))
    val state: State<ClockState> = _state


    init {
        updateTime()
    }
    private fun updateTime() {
        viewModelScope.launch {
            while (true) {
                val localTime = LocalTime.now()
                _state.value = ClockState(localTime.hour, localTime.minute, localTime.second)
                delay(1000)
            }
        }
    }
}