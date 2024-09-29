package com.example.presenter.ui.jackpot.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.digitain_test_task.presenter.R
import com.example.presenter.components.item.JackpotDetailItem
import com.example.presenter.model.jackpot.CardSuitUiModel
import com.example.presenter.model.jackpot.TopMonthlyWinnerUiModel
import com.example.presenter.theme.DigitainTheme
import java.math.BigDecimal

@Composable
fun JackpotBottomSheet(
    modifier: Modifier = Modifier,
    cardSuitUiModelList: List<CardSuitUiModel?>? = null
) {
    JackpotBottomSheetContent(
        modifier = modifier,
        cardSuitUiModelList = cardSuitUiModelList
    )
}

@Composable
fun JackpotBottomSheetContent(
    modifier: Modifier = Modifier,
    cardSuitUiModelList: List<CardSuitUiModel?>? = null
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        itemsIndexed(
            items = cardSuitUiModelList.orEmpty(),
            key = { _, cardSuit ->
                cardSuit.toString()
            }
        ) { _, cardSuit ->
            JackpotDetailItem(
                modifier = Modifier,
                cardSuit = cardSuit
            )
        }
    }
}

@Preview
@Composable
fun JackpotBottomSheetPreview() {
    DigitainTheme {
        JackpotBottomSheetContent(
            cardSuitUiModelList = listOf(
                CardSuitUiModel(
                    current = BigDecimal("12345.67"),
                    previous = BigDecimal("12000.55"),
                    largestWin = BigDecimal("50000.89"),
                    largestWinString = "50,000.89",
                    largestWinDate = "8/25/2024 2:38:49 PM +03:00",
                    largestWinUser = "100500",
                    lastWin = BigDecimal("45000.00"),
                    lastWinString = "45,000.00",
                    lastWinDate = "8/25/2024 2:38:49 PM +03:00",
                    lastWinUser = "100500",
                    topMonthlyWinnerUiModelList = listOf(
                        TopMonthlyWinnerUiModel(
                            winAmount = BigDecimal("10000.50"),
                            winDate = "2024-09-20",
                            winUser = "John Doe"
                        ),
                        TopMonthlyWinnerUiModel(
                            winAmount = BigDecimal("10000.50"),
                            winDate = "2024-09-20",
                            winUser = "John Doe"
                        )
                    ),
                    topYearlyWinners = listOf("Alice", "Bob", "Charlie"),
                    wins = 20,
                    backgroundRes = R.drawable.bg_diamond,
                    iconRes = R.drawable.ic_diamond,
                    name = "Diamond",
                    startIntegerDigits = listOf(1, 2, 3, 4, 5),
                    startFractionalDigits = listOf(6, 7),
                    endIntegerDigits = listOf(1, 2, 3, 4, 6),
                    endFractionalDigits = listOf(7, 9)
                )
            )
        )
    }
}