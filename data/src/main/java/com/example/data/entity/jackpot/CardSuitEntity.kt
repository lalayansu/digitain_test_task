package com.example.data.entity.jackpot

import java.math.BigDecimal

data class CardSuitEntity(
    val current: BigDecimal? = null,
    val largestWin: BigDecimal? = null,
    val largestWinDate: String? = null,
    val largestWinUser: String? = null,
    val lastWin: BigDecimal? = null,
    val lastWinDate: String? = null,
    val lastWinUser: String? = null,
    val topMonthlyWinnerEntityList: List<TopMonthlyWinnerEntity?>? = null,
    val topYearlyWinners: Any? = null,
    val wins: Int? = null,
)