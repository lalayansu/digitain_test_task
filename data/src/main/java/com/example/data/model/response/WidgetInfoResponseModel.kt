package com.example.data.model.response

import com.google.gson.annotations.SerializedName

data class WidgetInfoResponseModel(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("levelId")
    val levelId: Int? = null,
    @SerializedName("lobbyWidgetSettingId")
    val lobbyWidgetSettingId: Int? = null,
    @SerializedName("order")
    val order: Int? = null,
    @SerializedName("state")
    val state: Int? = null
)