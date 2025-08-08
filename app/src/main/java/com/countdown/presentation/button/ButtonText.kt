package com.countdown.presentation.button

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.countdown.R
import com.countdown.ui.theme.RedPrimary

@Composable
fun ButtonTextAction(
    text: String,
    onClick: () -> Unit,
    color: Color = RedPrimary
){
    Button(onClick = { onClick() }, colors = ButtonDefaults.buttonColors(containerColor = color)) {
        Text(stringResource(id = R.string.accept_button_text), color = MaterialTheme.colorScheme.onPrimary)
    }
}