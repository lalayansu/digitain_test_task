package com.example.presenter.model.jackpot

import com.example.domain.model.jackpot.JackpotWidget

data class JackpotWidgetUiModel(
    val name: String? = null,
    val jackpotWidgetInfoUiModelList: List<JackpotWidgetInfoUiModel?>? = null
)

fun JackpotWidget.toUiModel() = JackpotWidgetUiModel(
    name = name,
    jackpotWidgetInfoUiModelList = jackpotWidgetInfoModelList?.map { jackpotWidgetInfo ->
        jackpotWidgetInfo?.toUiModel()
    }
)
