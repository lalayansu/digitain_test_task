package com.example.domain.model.jackpot

import java.math.BigDecimal

data class CardSuit(
    val current: BigDecimal? = null,
    val previous: BigDecimal? = null,
    val largestWin: BigDecimal? = null,
    val largestWinDate: String? = null,
    val largestWinUser: String? = null,
    val lastWin: BigDecimal? = null,
    val lastWinDate: String? = null,
    val lastWinUser: String? = null,
    val topMonthlyWinnerList: List<TopMonthlyWinner?>? = null,
    val topYearlyWinners: Any? = null,
    val wins: Int? = null,
)