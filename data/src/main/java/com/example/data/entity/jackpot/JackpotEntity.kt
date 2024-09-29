package com.example.data.entity.jackpot

data class JackpotEntity(
    val digitsAfterPoint: Int? = null,
    val currency: String? = null,
    val currencySymbol: String? = null,
    val jackpotWidgetEntity: JackpotWidgetEntity? = null,
    val cardSuitEntityList: List<CardSuitEntity?>? = null
)