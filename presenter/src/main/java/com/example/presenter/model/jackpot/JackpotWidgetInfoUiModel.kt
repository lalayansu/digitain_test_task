package com.example.presenter.model.jackpot

import com.example.domain.model.jackpot.JackpotWidgetInfo

data class JackpotWidgetInfoUiModel(
    val id: Int? = null,
    val levelId: Int? = null,
    val lobbyWidgetSettingId: Int? = null,
    val order: Int? = null,
    val state: Int? = null
)

fun JackpotWidgetInfo.toUiModel() = JackpotWidgetInfoUiModel(
    id = id,
    levelId = levelId,
    lobbyWidgetSettingId = lobbyWidgetSettingId,
    order = order,
    state = state
)