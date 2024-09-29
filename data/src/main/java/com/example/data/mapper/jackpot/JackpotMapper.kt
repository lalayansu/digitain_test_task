package com.example.data.mapper.jackpot

import com.example.data.entity.jackpot.JackpotEntity
import com.example.data.mapper.Mapper
import com.example.data.mapper.jackpot.suit.CardSuitMapper
import com.example.data.mapper.jackpot.widget.JackpotWidgetMapper
import com.example.domain.model.jackpot.Jackpot
import javax.inject.Inject

class JackpotMapper @Inject constructor(
    private val jackpotWidgetMapper: JackpotWidgetMapper,
    private val cardSuitMapper: CardSuitMapper
) : Mapper<JackpotEntity, Jackpot> {
    override fun toDomain(entity: JackpotEntity) = Jackpot(
        digitsAfterPoint = entity.digitsAfterPoint,
        currency = entity.currency,
        currencySymbol = entity.currencySymbol,
        jackpotWidget = entity.jackpotWidgetEntity?.let { jackpotWidgetEntityNonNullable ->
            jackpotWidgetMapper.toDomain(jackpotWidgetEntityNonNullable)
        },
        cardSuitList = entity.cardSuitEntityList?.map { cardSuitEntity ->
            cardSuitEntity?.let { cardSuitEntityNonNullable ->
                cardSuitMapper.toDomain(cardSuitEntityNonNullable)
            }
        }
    )

    override fun toEntity(model: Jackpot) = JackpotEntity(
        digitsAfterPoint = model.digitsAfterPoint,
        currency = model.currency,
        currencySymbol = model.currencySymbol,
        jackpotWidgetEntity = model.jackpotWidget?.let { widget ->
            jackpotWidgetMapper.toEntity(widget)
        },
        cardSuitEntityList = model.cardSuitList?.map { cardSuit ->
            cardSuit?.let { cardSuitNonNullable ->
                cardSuitMapper.toEntity(cardSuitNonNullable)
            }
        }?.sortedByDescending { cardSuitEntityNonNullable -> cardSuitEntityNonNullable?.current }
    )
}