package com.example.data.model.response


import com.google.gson.annotations.SerializedName

data class JackpotResponseModel(
    @SerializedName("digitsAfterPoint")
    val digitsAfterPoint: Int? = null,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("currencySymbol")
    val currencySymbol: String? = null,
    @SerializedName("jackpotWidget")
    val jackpotWidgetResponseModel: JackpotWidgetResponseModel? = null,
    @SerializedName("diamonds")
    val diamondsModel: CardSuitResponseModel? = null,
    @SerializedName("hearts")
    val heartsModel: CardSuitResponseModel? = null,
    @SerializedName("spades")
    val spadesModel: CardSuitResponseModel? = null,
    @SerializedName("clubs")
    val clubsModel: CardSuitResponseModel? = null
)