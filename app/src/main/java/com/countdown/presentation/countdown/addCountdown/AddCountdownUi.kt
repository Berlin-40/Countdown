package com.countdown.presentation.countdown.addCountdown

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.countdown.R
import com.countdown.presentation.button.ButtonTextAction
import com.countdown.presentation.component.Picker.MyTimePicker
import com.countdown.presentation.component.Picker.MyonDateSelected
import com.countdown.ui.theme.CountdownTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddCountdownUi(
    onNavigateBack: () -> Unit,
    onAccept: (String, String) -> Unit, // TODO: Add date and time parameters
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.add_countdown_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier.fillMaxWidth(),
            )
            // TODO: Ajouter un label pour le champ de description

            // TODO: Add Date and Time pickers here

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = onCancel, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                    Text(stringResource(id = R.string.cancel_button_text), color = MaterialTheme.colorScheme.onError)
                }

                Spacer(modifier = Modifier.width(8.dp))
                ButtonTextAction(
                    text = stringResource(id = R.string.accept_button_text),
                    onClick = { onAccept(title, description) },
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
@Composable
fun CountdownForm(
    onDateSelected: (Date) -> Unit,
    onTimeSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var dateText by remember { mutableStateOf("") }
    var timeText by remember { mutableStateOf("") }

    // Sélecteur de date
    val datePicker = MyonDateSelected(
        onDateSelected = { selectedDate ->
            dateText = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate)
            onDateSelected(selectedDate)
        },
        context
    )

    // Sélecteur d’heure
    val timePicker = MyTimePicker(
        onTimeSelected = { selectedTime ->
            timeText = selectedTime
            onTimeSelected(selectedTime)
        },
        context
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick = { datePicker.show() }) {
            Text(text = if (dateText.isEmpty()) "Sélectionner une date" else dateText)
        }

        Button(onClick = { timePicker.show() }) {
            Text(text = if (timeText.isEmpty()) "Sélectionner une heure" else timeText)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddCountdownUiPreview() {
    CountdownTheme {
        AddCountdownUi(
            onNavigateBack = {},
            onAccept = { _, _ -> },
            onCancel = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CountdownFormPreview() {
    CountdownTheme {
        CountdownForm(
            onDateSelected = {},
            onTimeSelected = {}
        )
    }
}
