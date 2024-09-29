package com.example.data.mapper.jackpot.suit

import com.example.data.entity.jackpot.CardSuitEntity
import com.example.data.mapper.ModelToEntityMapper
import com.example.data.mapper.jackpot.suit.topmonthlywinner.TopMonthlyWinnerModelToEntityMapper
import com.example.data.model.response.CardSuitResponseModel
import javax.inject.Inject

class CardSuitModelToEntityMapper @Inject constructor(
    private val topMonthlyWinnerModelToEntityMapper: TopMonthlyWinnerModelToEntityMapper
) : ModelToEntityMapper<CardSuitResponseModel, CardSuitEntity> {

    override fun toEntity(model: CardSuitResponseModel) = CardSuitEntity(
        current = model.current,
        largestWin = model.largestWin,
        largestWinDate = model.largestWinDate,
        largestWinUser = model.largestWinUser,
        lastWin = model.lastWin,
        lastWinDate = model.lastWinDate,
        lastWinUser = model.lastWinUser,
        topMonthlyWinnerEntityList = model.topMonthlyWinnerResponseModelList?.map { topMonthlyWinnerModel ->
            topMonthlyWinnerModel?.let { topMonthlyWinnerModelNonNullable ->
                topMonthlyWinnerModelToEntityMapper.toEntity(topMonthlyWinnerModelNonNullable)
            }
        },
        topYearlyWinners = model.topYearlyWinners,
        wins = model.wins,
    )
}