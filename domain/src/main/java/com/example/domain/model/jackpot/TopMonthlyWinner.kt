package com.example.domain.model.jackpot

import java.math.BigDecimal

data class TopMonthlyWinner(
    val winAmount: BigDecimal? = null,
    val winDate: String? = null,
    val winUser: String? = null
)