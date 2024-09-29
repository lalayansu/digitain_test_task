package com.example.data.mapper.jackpot.suit

import com.example.data.entity.jackpot.CardSuitEntity
import com.example.data.mapper.Mapper
import com.example.data.mapper.jackpot.suit.topmonthlywinner.TopMonthlyWinnerMapper
import com.example.domain.model.jackpot.CardSuit
import javax.inject.Inject

class CardSuitMapper @Inject constructor(
    private val topMonthlyWinnerMapper: TopMonthlyWinnerMapper
) : Mapper<CardSuitEntity, CardSuit> {
    override fun toDomain(entity: CardSuitEntity) = CardSuit(
        current = entity.current,
        largestWin = entity.largestWin,
        largestWinDate = entity.largestWinDate,
        largestWinUser = entity.largestWinUser,
        lastWin = entity.lastWin,
        lastWinDate = entity.lastWinDate,
        lastWinUser = entity.lastWinUser,
        topMonthlyWinnerList = entity.topMonthlyWinnerEntityList?.map { topMonthlyWinnerEntity ->
            topMonthlyWinnerEntity?.let { monthlyWinnerEntityNonNullable ->
                topMonthlyWinnerMapper.toDomain(monthlyWinnerEntityNonNullable)
            }
        },
        topYearlyWinners = entity.topYearlyWinners,
        wins = entity.wins,
    )

    override fun toEntity(model: CardSuit) = CardSuitEntity(
        current = model.current,
        largestWin = model.largestWin,
        largestWinDate = model.largestWinDate,
        largestWinUser = model.largestWinUser,
        lastWin = model.lastWin,
        lastWinDate = model.lastWinDate,
        lastWinUser = model.lastWinUser,
        topMonthlyWinnerEntityList = model.topMonthlyWinnerList?.map { monthlyWinner ->
            monthlyWinner?.let { topMonthlyWinnerNonNullable ->
                topMonthlyWinnerMapper.toEntity(topMonthlyWinnerNonNullable)
            }
        },
        topYearlyWinners = model.topYearlyWinners,
        wins = model.wins,
    )
}