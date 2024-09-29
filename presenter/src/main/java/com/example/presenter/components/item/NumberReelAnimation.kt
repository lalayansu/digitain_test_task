package com.example.presenter.components.item

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun NumberReelAnimation(
    modifier: Modifier = Modifier,
    startIntegerPart: List<Int>,
    endIntegerPart: List<Int>,
    startFractionalPart: List<Int>,
    endFractionalPart: List<Int>,
    durationMillisPerDigit: Int = 150
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimateReelingDigits(
            startDigits = startIntegerPart,
            endDigits = endIntegerPart,
            durationMillisPerDigit
        )

        if (startFractionalPart.isNotEmpty() || endFractionalPart.isNotEmpty()) {
            Text(text = ".", style = MaterialTheme.typography.headlineSmall)
        }

        AnimateReelingDigits(
            startDigits = startFractionalPart,
            endDigits = endFractionalPart,
            durationMillisPerDigit
        )
    }
}

@Composable
fun AnimateReelingDigits(
    startDigits: List<Int>,
    endDigits: List<Int>,
    durationMillisPerDigit: Int
) {
    startDigits.zip(endDigits).forEach { (startDigit, endDigit) ->
        if (startDigit == -1 && endDigit == -1) {
            Text(
                text = " ",
                style = MaterialTheme.typography.headlineSmall
            )
        } else {
            ReelingDigit(
                startDigit = startDigit,
                endDigit = endDigit,
                durationMillisPerDigit = durationMillisPerDigit
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ReelingDigit(startDigit: Int, endDigit: Int, durationMillisPerDigit: Int) {
    if (startDigit == -1 || endDigit == -1) return

    var currentDigit by remember { mutableStateOf(startDigit) }
    val digitCount =
        if (endDigit >= startDigit) endDigit - startDigit + 1 else (10 - startDigit + endDigit + 1)

    LaunchedEffect(endDigit) {
        for (step in 0 until digitCount) {
            delay(durationMillisPerDigit.toLong())
            currentDigit = (startDigit + step) % 10
        }
    }

    AnimatedContent(
        targetState = currentDigit.toString(),
        transitionSpec = {
            (slideInVertically { it } + fadeIn(tween(durationMillis = 300))) with
                    (slideOutVertically { -it } + fadeOut(tween(durationMillis = 300)))
        },
        label = "Reeling Digit"
    ) { char ->
        Text(
            text = char,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}