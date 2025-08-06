package com.countdown.presentation.component.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun BubbleAnimation() {
    val bubbleCount = 10
    val bubbles = remember { List(bubbleCount) { Bubble() } }

    Box(modifier = Modifier.fillMaxSize()
        .padding(horizontal = 30.dp) ,
        contentAlignment = Alignment.Center) {
        bubbles.forEach { bubble ->
            AnimatedBubble(bubble)
        }
    }
}

@Composable
fun AnimatedBubble(bubble: Bubble) {
    val infiniteTransition = rememberInfiniteTransition(label = "bubble transition")

    val yOffset by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = bubble.duration,
                easing = LinearEasing,
                delayMillis = bubble.delay
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "y offset"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                translationX = bubble.x * size.width
                translationY = yOffset * size.height/1.5f
                alpha = 1f - yOffset
            }
    ) {
        Box(
            modifier = Modifier
                .offset(x = 0.dp, y = 0.dp)
                .size(bubble.size.dp)
                .background(bubble.color, shape = CircleShape)
        )
    }
}

data class Bubble(
    val x: Float = Random.nextFloat(),
    val size: Int = Random.nextInt(10, 40),
    val duration: Int = Random.nextInt(3000, 6000),
    val delay: Int = Random.nextInt(0, 3000),
    val color: Color = if (Random.nextBoolean()) Color.White.copy(alpha = 0.5f) else Color.Gray.copy(alpha = 0.5f)

)
