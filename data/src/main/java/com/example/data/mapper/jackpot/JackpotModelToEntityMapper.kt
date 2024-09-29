package com.example.data.mapper.jackpot

import com.example.data.entity.jackpot.JackpotEntity
import com.example.data.mapper.ModelToEntityMapper
import com.example.data.mapper.jackpot.suit.CardSuitModelToEntityMapper
import com.example.data.mapper.jackpot.widget.JackpotWidgetModelToEntityMapper
import com.example.data.model.response.JackpotResponseModel
import javax.inject.Inject

class JackpotModelToEntityMapper @Inject constructor(
    private val jackpotWidgetModelToEntityMapper: JackpotWidgetModelToEntityMapper,
    private val cardsSuitModelToEntityMapper: CardSuitModelToEntityMapper
) : ModelToEntityMapper<JackpotResponseModel, JackpotEntity> {

    override fun toEntity(model: JackpotResponseModel) = JackpotEntity(
        digitsAfterPoint = model.digitsAfterPoint,
        currency = model.currency,
        currencySymbol = model.currencySymbol,
        jackpotWidgetEntity = model.jackpotWidgetResponseModel?.let { widgetModel ->
            jackpotWidgetModelToEntityMapper.toEntity(widgetModel)
        },
        cardSuitEntityList = listOfNotNull(
            model.clubsModel?.let { cardSuitModelNonNullable ->
                cardsSuitModelToEntityMapper.toEntity(
                    model = cardSuitModelNonNullable,
                )
            },
            model.diamondsModel?.let { cardSuitModelNonNullable ->
                cardsSuitModelToEntityMapper.toEntity(
                    model = cardSuitModelNonNullable,
                )
            },
            model.heartsModel?.let { cardSuitModelNonNullable ->
                cardsSuitModelToEntityMapper.toEntity(
                    model = cardSuitModelNonNullable,
                )
            },
            model.spadesModel?.let { cardSuitModelNonNullable ->
                cardsSuitModelToEntityMapper.toEntity(
                    model = cardSuitModelNonNullable,
                )
            }
        ).sortedByDescending { cardSuitEntity -> cardSuitEntity.current }
    )
}