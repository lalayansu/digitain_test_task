package com.example.data.model.response

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class CardSuitResponseModel(
    @SerializedName("current")
    val current: BigDecimal? = null,
    @SerializedName("largestWin")
    val largestWin: BigDecimal? = null,
    @SerializedName("largestWinDate")
    val largestWinDate: String? = null,
    @SerializedName("largestWinUser")
    val largestWinUser: String? = null,
    @SerializedName("lastWin")
    val lastWin: BigDecimal? = null,
    @SerializedName("lastWinDate")
    val lastWinDate: String? = null,
    @SerializedName("lastWinUser")
    val lastWinUser: String? = null,
    @SerializedName("topMonthlyWinners")
    val topMonthlyWinnerResponseModelList: List<TopMonthlyWinnerResponseModel?>? = null,
    @SerializedName("topYearlyWinners")
    val topYearlyWinners: Any? = null,
    @SerializedName("wins")
    val wins: Int? = null
)