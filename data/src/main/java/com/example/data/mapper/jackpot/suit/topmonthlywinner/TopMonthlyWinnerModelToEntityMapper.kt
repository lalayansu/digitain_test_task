package com.example.data.mapper.jackpot.suit.topmonthlywinner

import com.example.data.entity.jackpot.TopMonthlyWinnerEntity
import com.example.data.mapper.ModelToEntityMapper
import com.example.data.model.response.TopMonthlyWinnerResponseModel
import javax.inject.Inject

class TopMonthlyWinnerModelToEntityMapper @Inject constructor() :
    ModelToEntityMapper<TopMonthlyWinnerResponseModel, TopMonthlyWinnerEntity> {

    override fun toEntity(model: TopMonthlyWinnerResponseModel) = TopMonthlyWinnerEntity(
        winAmount = model.winAmount,
        winDate = model.winDate,
        winUser = model.winUser,
    )
}