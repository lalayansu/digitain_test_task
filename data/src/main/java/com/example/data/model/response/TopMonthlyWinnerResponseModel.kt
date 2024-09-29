package com.example.data.model.response

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class TopMonthlyWinnerResponseModel(
    @SerializedName("winAmount")
    val winAmount: BigDecimal? = null,
    @SerializedName("winDate")
    val winDate: String? = null,
    @SerializedName("winUser")
    val winUser: String? = null
)