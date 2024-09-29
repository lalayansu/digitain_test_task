package com.example.data.repository.jackpot

import com.example.data.mapper.jackpot.JackpotMapper
import com.example.data.mapper.jackpot.JackpotModelToEntityMapper
import com.example.data.service.JackpotService
import com.example.domain.repository.JackpotRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JackpotRepositoryImpl @Inject constructor(
    private val jackpotService: JackpotService,
    private val jackpotMapper: JackpotMapper,
    private val jackpotModelToEntityMapper: JackpotModelToEntityMapper
) : JackpotRepository {

    override suspend fun getJackpot() = jackpotMapper.toDomain(
        jackpotModelToEntityMapper.toEntity(model = jackpotService.getJackpot())
    )
}