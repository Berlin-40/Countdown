package com.countdown.presentation.component.cards

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.countdown.presentation.component.Text.TextMinuteur
import com.countdown.presentation.component.scrollList.CircularScrollList
import com.countdown.presentation.minuteur.MinuteurAction
import com.countdown.ui.theme.RedPrimary

@Composable
fun CardMinuteur(
    seconde: String,
    minute: String ,
    started: Boolean,
    onAction: (MinuteurAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val normalSize = 140.dp

    val t by animateDpAsState(
        targetValue = if(started) 200.dp else normalSize,
        animationSpec = tween(1000),
        label = "t animation"
    )
    Card(
        shape =RoundedCornerShape(30.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = RedPrimary,
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(t)

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
                    text = "Minutes",
                    style = MaterialTheme.typography.titleMedium
                )
                if(!started){
                    CircularScrollList(range = 0..59) { number ->
                        onAction(MinuteurAction.SetMinute(number))
                    }
                }else{
                    Box(
                        modifier = Modifier
                            .height(t),
                        contentAlignment = Alignment.Center
                    ) {
                        TextMinuteur(minute)
                    }
                }
            }

            TextMinuteur(":")
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Secondes",
                    style = MaterialTheme.typography.titleMedium
                )
                if(!started){
                    CircularScrollList(range = 0..59) { number ->
                        onAction(MinuteurAction.SetSeconde(number))
                    }
                }else{
                    Box(
                        modifier = Modifier
                            .height(t),
                        contentAlignment = Alignment.Center
                    ) {
                        TextMinuteur(seconde)
                    }
                }
            }
        }
    }
}