package com.example.domain.model.jackpot


data class Jackpot(
    val digitsAfterPoint: Int? = null,
    val currency: String? = null,
    val currencySymbol: String? = null,
    val jackpotWidget: JackpotWidget? = null,
    val cardSuitList: List<CardSuit?>? = null
)