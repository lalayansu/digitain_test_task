package com.example.presenter.model.jackpot

import com.example.domain.model.jackpot.CardSuit
import com.example.presenter.utils.formatToSeparatedString
import com.example.presenter.utils.formatWithDigits
import com.example.presenter.utils.prepareBigDecimalForReel
import java.math.BigDecimal

data class CardSuitUiModel(
    val current: BigDecimal? = null,
    val previous: BigDecimal? = null,
    val largestWin: BigDecimal? = null,
    val largestWinString: String? = null,
    val largestWinDate: String? = null,
    val largestWinUser: String? = null,
    val lastWin: BigDecimal? = null,
    val lastWinString: String? = null,
    val lastWinDate: String? = null,
    val lastWinUser: String? = null,
    val topMonthlyWinnerUiModelList: List<TopMonthlyWinnerUiModel?>? = null,
    val topYearlyWinners: Any? = null,
    val wins: Int? = null,
    val backgroundRes: Int? = null,
    val iconRes: Int? = null,
    val name: String? = null,
    val startIntegerDigits: List<Int>,
    val startFractionalDigits: List<Int>,
    val endIntegerDigits: List<Int>,
    val endFractionalDigits: List<Int>,
)

fun CardSuit.toUiModel(
    backgroundRes: Int,
    iconRes: Int,
    name: String,
    digitCount: Int
): CardSuitUiModel {
    val (startSplit, endSplit) = prepareBigDecimalForReel(
        startNumber = previous?.formatWithDigits(digitCount = digitCount) ?: BigDecimal.ZERO,
        endNumber = current?.formatWithDigits(digitCount = digitCount) ?: BigDecimal.ZERO
    )

    val startIntegerDigits = startSplit.first
    val startFractionalDigits = startSplit.second
    val endIntegerDigits = endSplit.first
    val endFractionalDigits = endSplit.second

    return CardSuitUiModel(
        current = current?.formatWithDigits(digitCount = digitCount),
        previous = previous?.formatWithDigits(digitCount = digitCount),
        largestWin = largestWin?.formatWithDigits(digitCount = digitCount),
        largestWinString = largestWin?.formatToSeparatedString(digitCount = digitCount),
        largestWinDate = largestWinDate,
        largestWinUser = largestWinUser,
        lastWin = lastWin?.formatWithDigits(digitCount = digitCount),
        lastWinString = largestWin?.formatToSeparatedString(digitCount = digitCount),
        lastWinDate = lastWinDate,
        lastWinUser = lastWinUser,
        topMonthlyWinnerUiModelList = topMonthlyWinnerList?.map { topMonthlyWinnerEntity ->
            topMonthlyWinnerEntity?.toUiModel()
        },
        topYearlyWinners = topYearlyWinners,
        wins = wins,
        backgroundRes = backgroundRes,
        iconRes = iconRes,
        name = name,
        startIntegerDigits = startIntegerDigits,
        startFractionalDigits = startFractionalDigits,
        endIntegerDigits = endIntegerDigits,
        endFractionalDigits = endFractionalDigits
    )
}