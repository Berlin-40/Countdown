package com.countdown.presentation.component.box

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.countdown.presentation.component.scrollList.CircularScrollList
import com.countdown.presentation.minuteur.MinuteurAction
import com.countdown.ui.theme.RedPrimary

@Composable
fun BoxMinuteur(
    minute: String,
    hours: String ,
    modifier: Modifier = Modifier
) {
    val t by animateDpAsState(
        targetValue = 200.dp,
        animationSpec = tween(1000),
        label = "t animation"
    )
    Card(
        shape =RoundedCornerShape(30.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = RedPrimary,
        ),
        modifier = Modifier.fillMaxWidth()
            .size(t)

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

                Text(
                    text = hours,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .height(100.dp)
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center
                )
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
                Text(
                    text = hours,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}