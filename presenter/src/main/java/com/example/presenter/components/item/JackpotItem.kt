package com.example.presenter.components.item

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.digitain_test_task.presenter.R
import com.example.presenter.theme.DigitainTheme

@Composable
fun JackpotItem(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    @DrawableRes backgroundRes: Int,
    startIntegerPart: List<Int>,
    endIntegerPart: List<Int>,
    startFractionalPart: List<Int>,
    endFractionalPart: List<Int>,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .height(56.dp)
            .clip(MaterialTheme.shapes.large)
            .clickable { onClick.invoke() },
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = backgroundRes),
            contentDescription = backgroundRes.toString(),
            contentScale = ContentScale.Crop
        )

        Image(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            painter = painterResource(id = iconRes),
            contentDescription = iconRes.toString()
        )

        NumberReelAnimation(
            modifier = Modifier
                .align(Alignment.Center),
            startIntegerPart = startIntegerPart,
            endIntegerPart = endIntegerPart,
            startFractionalPart = startFractionalPart,
            endFractionalPart = endFractionalPart
        )
    }
}

@Preview
@Composable
fun JackpotItemPreview() {
    DigitainTheme {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                JackpotItem(
                    startIntegerPart = listOf(1, 2, 3, 4),
                    endIntegerPart = listOf(5, 6, 7, 8),
                    startFractionalPart = listOf(1, 2, 3, 4),
                    endFractionalPart = listOf(5, 6, 7, 8),
                    iconRes = R.drawable.ic_diamond,
                    backgroundRes = R.drawable.bg_diamond
                )
            }

            item {
                JackpotItem(
                    startIntegerPart = listOf(1, 2, 3, 4),
                    endIntegerPart = listOf(5, 6, 7, 8),
                    startFractionalPart = listOf(1, 2, 3, 4),
                    endFractionalPart = listOf(5, 6, 7, 8),
                    iconRes = R.drawable.ic_gold,
                    backgroundRes = R.drawable.bg_gold
                )
            }

            item {
                JackpotItem(
                    startIntegerPart = listOf(1, 2, 3, 4),
                    endIntegerPart = listOf(5, 6, 7, 8),
                    startFractionalPart = listOf(1, 2, 3, 4),
                    endFractionalPart = listOf(5, 6, 7, 8),
                    iconRes = R.drawable.ic_silver,
                    backgroundRes = R.drawable.bg_silver
                )
            }

            item {
                JackpotItem(
                    startIntegerPart = listOf(1, 2, 3, 4),
                    endIntegerPart = listOf(5, 6, 7, 8),
                    startFractionalPart = listOf(1, 2, 3, 4),
                    endFractionalPart = listOf(5, 6, 7, 8),
                    iconRes = R.drawable.ic_bronze,
                    backgroundRes = R.drawable.bg_bronze
                )
            }
        }
    }
}
