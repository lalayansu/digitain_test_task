package com.example.presenter.components.brush

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush

@Composable
fun shimmerBrushForHorizontalItems(): Brush {
    val shimmerColors = listOf(
        MaterialTheme.colorScheme.outline.copy(alpha = 0.6f),
        MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
        MaterialTheme.colorScheme.outline.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition(label = "shimmer")

    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        ),
        label = "translateAnimation"
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(x = -translateAnimation.value, y = 0f),
        end = Offset(x = translateAnimation.value, y = 0f)
    )
}