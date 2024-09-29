package com.example.data.mapper.jackpot.widget

import com.example.data.entity.jackpot.JackpotWidgetEntity
import com.example.data.mapper.ModelToEntityMapper
import com.example.data.mapper.jackpot.widget.info.JackpotWidgetInfoModelToEntityMapper
import com.example.data.model.response.JackpotWidgetResponseModel
import javax.inject.Inject

class JackpotWidgetModelToEntityMapper @Inject constructor(
    private val widgetInfoModelToEntityMapper: JackpotWidgetInfoModelToEntityMapper
) : ModelToEntityMapper<JackpotWidgetResponseModel, JackpotWidgetEntity> {

    override fun toEntity(model: JackpotWidgetResponseModel) = JackpotWidgetEntity(
        name = model.name,
        jackpotWidgetInfoEntityList = model.jackpotWidgetInfoResponseModelList?.map { widgetInfoModel ->
            widgetInfoModel?.let { widgetInfoModelNonNullable ->
                widgetInfoModelToEntityMapper.toEntity(widgetInfoModelNonNullable)
            }
        }
    )
}