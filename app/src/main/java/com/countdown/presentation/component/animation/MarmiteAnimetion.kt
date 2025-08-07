package com.countdown.presentation.component.animation

import android.R.attr.x
import android.R.color.white
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.countdown.R
import com.countdown.presentation.minuteur.MinuteurAction
import com.countdown.ui.theme.Black
import com.countdown.ui.theme.RedPrimary
import com.countdown.ui.theme.RedSecondary
import com.countdown.ui.theme.Rose
import com.countdown.ui.theme.White

@Composable
fun MarmiteAnimation(
    start: Boolean,
    onAction: (MinuteurAction) -> Unit = {},
    duration:Int,
    modifier: Modifier = Modifier
){

    val t by animateFloatAsState(
        targetValue = if (start) 0f else 1f,
        animationSpec = tween(1000),
        label = "t animation"
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (start) RedPrimary else Rose,
        animationSpec = tween(durationMillis = 1000),
        label = "color transition"
    )
    val state = if(start) "Start" else "Stop"
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
    ) {
        val distance = 40.dp

        val infiniteAlpha by rememberInfiniteTransition().animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1050),
                repeatMode = RepeatMode.Reverse
            )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.vapeur),
                contentDescription = "Vapeur",
                modifier = Modifier
                    .size(80.dp)
                    .offset(y = -120.dp)
                    .graphicsLayer { this.alpha =
                        if(start) infiniteAlpha else 0f
                    },
                )
            // Images animées par-dessus ou dessous
            Icon(
                painter = painterResource(id = R.drawable.haut),
                contentDescription = "Marmite haut",
                modifier = Modifier
                    .size(180.dp)
                    .offset(y = -distance * t-45.dp)
                    .clip(RoundedCornerShape(30.dp)) ,
                tint = backgroundColor
            )
            Image(
                painter = painterResource(id = R.drawable.bas),
                contentDescription = "Marmite bas",
                modifier = Modifier
                    .size(200.dp)
                    .offset(y = distance * t+30.dp)
                    .clip(RoundedCornerShape(30.dp)) ,
                colorFilter = ColorFilter.tint(backgroundColor)
            )
            // Texte toujours centré, non affecté par l’animation
            Text(
                text = state,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .clickable {
                        if (start) {
                            onAction(MinuteurAction.Stop)
                        } else {
                            onAction(MinuteurAction.Start)
                        }

                    }
                )
            if(duration < 30 && start){
                BubbleAnimation()
            }
        }
    }
}

