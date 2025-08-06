package com.countdown.presentation.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.countdown.ui.theme.Black

@Composable
fun bottombar(
    destinations: List<String> = emptyList(),
    onNavigateToDestination: (String) -> Unit = {},
    currentRoute: String = "",
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        destinations.forEach { destination ->
            TextButton(
                onClick = { onNavigateToDestination(destination) },
            ) {
                Text(
                    text = destination,
                    style = if(destination == currentRoute)TextStyle(textDecoration = TextDecoration.Underline) else TextStyle(),
                    color = Black,
                    fontWeight = if(destination == currentRoute) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Normal
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun bottombarPreview(){

    val destinations = listOf("Home", "Profile", "Settings")
    var currentRoute by remember { mutableStateOf("Home") }
    fun onNavigateToDestination(destination: String) {
        currentRoute =  destination
    }
    bottombar(
        destinations = destinations,
        onNavigateToDestination = { onNavigateToDestination(it) },
        currentRoute = currentRoute
    )
}