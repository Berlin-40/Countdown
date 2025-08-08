package com.countdown.presentation.component.Picker

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun MyonDateSelected(onDateSelected: (Date) -> Unit,
                     context: Context): DatePickerDialog {
    val calendar = Calendar.getInstance()
    var dateText by remember { mutableStateOf("") }

    // Sélecteur de date
    return DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            val date = calendar.time
            dateText = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
            onDateSelected(date)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
}