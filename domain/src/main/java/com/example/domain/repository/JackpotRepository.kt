package com.example.domain.repository

import com.example.domain.model.jackpot.Jackpot

interface JackpotRepository {
    suspend fun getJackpot(): Jackpot?
}