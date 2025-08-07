package com.countdown.presentation.component.Text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextMinuteur(
    duration: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = duration,
        fontSize = 38.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier
            .padding(vertical = 16.dp),
        textAlign = TextAlign.Center
    )
}