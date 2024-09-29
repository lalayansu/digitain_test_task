package com.example.data.model.response

import com.google.gson.annotations.SerializedName

data class JackpotWidgetResponseModel(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("widgetInfos")
    val jackpotWidgetInfoResponseModelList: List<WidgetInfoResponseModel?>? = null
)