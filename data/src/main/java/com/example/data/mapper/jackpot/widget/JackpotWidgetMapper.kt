package com.example.data.mapper.jackpot.widget

import com.example.data.entity.jackpot.JackpotWidgetEntity
import com.example.data.mapper.Mapper
import com.example.data.mapper.jackpot.widget.info.JackpotWidgetInfoMapper
import com.example.domain.model.jackpot.JackpotWidget
import javax.inject.Inject

class JackpotWidgetMapper @Inject constructor(
    private val jackpotWidgetInfoMapper: JackpotWidgetInfoMapper
) : Mapper<JackpotWidgetEntity, JackpotWidget> {
    override fun toDomain(entity: JackpotWidgetEntity) = JackpotWidget(
        name = entity.name,
        jackpotWidgetInfoModelList = entity.jackpotWidgetInfoEntityList?.map { jackpotWidgetInfoEntity ->
            jackpotWidgetInfoEntity?.let { jackpotWidgetInfoEntityNonNullable ->
                jackpotWidgetInfoMapper.toDomain(jackpotWidgetInfoEntityNonNullable)
            }
        }
    )

    override fun toEntity(model: JackpotWidget) = JackpotWidgetEntity()
}