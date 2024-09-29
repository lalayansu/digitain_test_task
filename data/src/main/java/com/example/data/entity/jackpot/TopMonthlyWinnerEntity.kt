package com.example.data.entity.jackpot

import java.math.BigDecimal

data class TopMonthlyWinnerEntity(
    val winAmount: BigDecimal? = null,
    val winDate: String? = null,
    val winUser: String? = null
)