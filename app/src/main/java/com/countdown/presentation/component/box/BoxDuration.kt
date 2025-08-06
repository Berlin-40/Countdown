package com.countdown.presentation.component.box

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.countdown.presentation.component.scrollList.CircularScrollList
import com.countdown.ui.theme.RedPrimary

@Composable
fun BoxDuration(
    minute: String = "",
    hours: String = "",
    modifier: Modifier = Modifier
){
    var selectedNumber1 by remember { mutableStateOf(0) }
    var selectedNumber2 by remember { mutableStateOf(0) }
    Box(
        modifier = modifier.padding(20.dp)

    ){
        Card(
            shape =RoundedCornerShape(30.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(
                containerColor = RedPrimary,
            ),
            modifier = Modifier.fillMaxWidth()

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Hours",
                        style = MaterialTheme.typography.titleMedium
                    )
                    CircularScrollList(range = 0..23) { number ->
                        selectedNumber1 = number
                    }
                }

                Text(
                    text = ":",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Minutes",
                        style = MaterialTheme.typography.titleMedium
                    )
                    CircularScrollList(range = 0..59) { number ->
                        selectedNumber2 = number
                    }
                }

            }
        }
    }
}