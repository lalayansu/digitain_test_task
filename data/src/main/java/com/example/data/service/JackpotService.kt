package com.example.data.service

import com.example.data.model.response.JackpotResponseModel
import retrofit2.http.GET

interface JackpotService {
    @GET("GetJackpot")
    suspend fun getJackpot(): JackpotResponseModel
}