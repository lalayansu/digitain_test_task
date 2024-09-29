package com.example.presenter.components.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.digitain_test_task.presenter.R
import com.example.presenter.model.jackpot.CardSuitUiModel
import com.example.presenter.theme.DigitainTheme

@Composable
fun JackpotDetailItem(
    modifier: Modifier = Modifier,
    cardSuit: CardSuitUiModel?,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.large
            )
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(
                        id = cardSuit?.iconRes ?: R.drawable.ic_diamond
                    ),
                    contentDescription = cardSuit?.name,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = cardSuit?.name.orEmpty(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Text(
                text = "Min Bet: ${cardSuit?.lastWinUser}",
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        NumberReelAnimation(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            startIntegerPart = cardSuit?.startIntegerDigits.orEmpty(),
            endIntegerPart = cardSuit?.endIntegerDigits.orEmpty(),
            startFractionalPart = cardSuit?.startFractionalDigits.orEmpty(),
            endFractionalPart = cardSuit?.endFractionalDigits.orEmpty()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (cardSuit?.largestWinString.isNullOrEmpty()) {
            NoWinnerItem()
        } else {
            JackpotWinSectionItem(
                title = "Biggest Win",
                amount = cardSuit?.largestWinString.toString(),
                date = cardSuit?.largestWinDate,
                betId = "Bet ID: ${cardSuit?.largestWinUser.toString()}"
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (cardSuit?.lastWinString.isNullOrEmpty()) {
            NoWinnerItem()
        } else {
            JackpotWinSectionItem(
                title = "Latest Win",
                amount = cardSuit?.lastWinString.toString(),
                date = cardSuit?.lastWinDate,
                betId = "Bet ID: ${cardSuit?.lastWinUser.toString()}"
            )
        }
    }
}

@Preview
@Composable
fun JackpotDetailItemPreview() {
    DigitainTheme {
        Column {
            JackpotDetailItem(
                cardSuit = CardSuitUiModel(
                    startIntegerDigits = listOf(1, 2, 3, 4),
                    endIntegerDigits = listOf(5, 6, 7, 8),
                    startFractionalDigits = listOf(1, 2, 3, 4),
                    endFractionalDigits = listOf(5, 6, 7, 8),
                    largestWinUser = 123456789.toString(),
                    largestWinDate = "8/25/2024 2:38:49 PM +03:00",
                    largestWinString = 123456789.toString(),
                    lastWinUser = 123456789.toString(),
                    lastWinDate = "8/25/2024 2:38:49 PM +03:00",
                    lastWinString = 123456789.toString(),
                    name = "Diamond",
                    iconRes = R.drawable.ic_diamond,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            JackpotDetailItem(
                cardSuit = CardSuitUiModel(
                    startIntegerDigits = listOf(1, 2, 3, 4),
                    endIntegerDigits = listOf(5, 6, 7, 8),
                    startFractionalDigits = listOf(1, 2, 3, 4),
                    endFractionalDigits = listOf(5, 6, 7, 8),
                    largestWinUser = 123456789.toString(),
                    largestWinDate = "8/25/2024 2:38:49 PM +03:00",
                    largestWinString = 123456789.toString(),
                    lastWinUser = 123456789.toString(),
                    lastWinDate = "8/25/2024 2:38:49 PM +03:00",
                    lastWinString = null,
                    name = "Diamond",
                    iconRes = R.drawable.ic_diamond,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            JackpotDetailItem(
                cardSuit = CardSuitUiModel(
                    startIntegerDigits = listOf(1, 2, 3, 4),
                    endIntegerDigits = listOf(5, 6, 7, 8),
                    startFractionalDigits = listOf(1, 2, 3, 4),
                    endFractionalDigits = listOf(5, 6, 7, 8),
                    largestWinUser = 123456789.toString(),
                    largestWinDate = "8/25/2024 2:38:49 PM +03:00",
                    largestWinString = null,
                    lastWinUser = 123456789.toString(),
                    lastWinDate = "8/25/2024 2:38:49 PM +03:00",
                    lastWinString = 123456789.toString(),
                    name = "Diamond",
                    iconRes = R.drawable.ic_diamond,
                )
            )
        }
    }
}
