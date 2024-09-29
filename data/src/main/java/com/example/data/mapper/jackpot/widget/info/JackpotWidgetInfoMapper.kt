package com.example.data.mapper.jackpot.widget.info

import com.example.data.entity.jackpot.JackpotWidgetInfoEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.jackpot.JackpotWidgetInfo
import javax.inject.Inject

class JackpotWidgetInfoMapper @Inject constructor() :
    Mapper<JackpotWidgetInfoEntity, JackpotWidgetInfo> {
    override fun toDomain(entity: JackpotWidgetInfoEntity) = JackpotWidgetInfo(
        id = entity.id,
        levelId = entity.levelId,
        lobbyWidgetSettingId = entity.lobbyWidgetSettingId,
        order = entity.order,
        state = entity.state,
    )

    override fun toEntity(model: JackpotWidgetInfo) = JackpotWidgetInfoEntity()
}