package com.countdown.presentation.component.bottombar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.countdown.ui.theme.Black
import com.countdown.ui.theme.RedSecondary

@Composable
fun BottomBarNav(
    destinations: List<String> = emptyList(),
    onNavigateToDestination: (String) -> Unit,
    currentRoute: String = "",
    modifier: Modifier = Modifier,
) {
    
    BottomAppBar(
        containerColor = RedSecondary
    ){
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
                        fontSize = 20.sp,
                        fontWeight = if(destination == currentRoute) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottombarPreview(){

    val destinations = listOf("Countdown", "Minuteur")
    var currentRoute by remember { mutableStateOf("Countdown") }
    fun onNavigateToDestination(destination: String) {
        currentRoute =  destination
    }
    BottomBarNav(
        destinations = destinations,
        onNavigateToDestination = { onNavigateToDestination(it) },
        currentRoute = currentRoute
    )
}