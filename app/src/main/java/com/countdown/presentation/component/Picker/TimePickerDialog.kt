package com.countdown.presentation.component.Picker

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.util.Calendar

@Composable
fun MyTimePicker(onTimeSelected: (String) -> Unit,
                 context: Context,): TimePickerDialog {
    val calendar = Calendar.getInstance()
    var timeText by remember { mutableStateOf("") }

    return TimePickerDialog(
        context,
        { _, hour, minute ->
            val timeString = String.format("%02d:%02d", hour, minute)
            timeText = timeString
            onTimeSelected(timeString)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )
}