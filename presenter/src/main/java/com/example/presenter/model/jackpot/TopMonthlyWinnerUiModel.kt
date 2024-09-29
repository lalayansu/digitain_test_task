package com.example.presenter.model.jackpot

import com.example.domain.model.jackpot.TopMonthlyWinner
import java.math.BigDecimal

data class TopMonthlyWinnerUiModel(
    val winAmount: BigDecimal? = null,
    val winDate: String? = null,
    val winUser: String? = null
)

fun TopMonthlyWinner.toUiModel() = TopMonthlyWinnerUiModel(
    winAmount = winAmount,
    winDate = winDate,
    winUser = winUser
)