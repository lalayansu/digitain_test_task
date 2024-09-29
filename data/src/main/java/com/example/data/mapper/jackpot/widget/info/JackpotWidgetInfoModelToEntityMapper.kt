package com.example.data.mapper.jackpot.widget.info

import com.example.data.entity.jackpot.JackpotWidgetInfoEntity
import com.example.data.mapper.ModelToEntityMapper
import com.example.data.model.response.WidgetInfoResponseModel
import javax.inject.Inject

class JackpotWidgetInfoModelToEntityMapper @Inject constructor() :
    ModelToEntityMapper<WidgetInfoResponseModel, JackpotWidgetInfoEntity> {

    override fun toEntity(model: WidgetInfoResponseModel) = JackpotWidgetInfoEntity(
        id = model.id,
        levelId = model.levelId,
        lobbyWidgetSettingId = model.lobbyWidgetSettingId,
        order = model.order,
        state = model.state
    )
}