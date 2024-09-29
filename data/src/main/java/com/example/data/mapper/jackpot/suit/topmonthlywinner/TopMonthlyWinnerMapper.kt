package com.example.data.mapper.jackpot.suit.topmonthlywinner

import com.example.data.entity.jackpot.TopMonthlyWinnerEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.jackpot.TopMonthlyWinner
import javax.inject.Inject

class TopMonthlyWinnerMapper @Inject constructor(
) : Mapper<TopMonthlyWinnerEntity, TopMonthlyWinner> {
    override fun toDomain(entity: TopMonthlyWinnerEntity) = TopMonthlyWinner(
        winAmount = entity.winAmount,
        winDate = entity.winDate,
        winUser = entity.winUser
    )

    override fun toEntity(model: TopMonthlyWinner) = TopMonthlyWinnerEntity()
}