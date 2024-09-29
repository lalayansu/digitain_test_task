package com.example.data.di

import com.example.data.repository.jackpot.JackpotRepositoryImpl
import com.example.data.service.JackpotService
import com.example.domain.repository.JackpotRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(
    includes = [
        DataModule.JackpotDeclarations::class
    ]
)
@InstallIn(SingletonComponent::class)
class DataModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class JackpotDeclarations {
        @Binds
        abstract fun provideJackpotRepository(repository: JackpotRepositoryImpl): JackpotRepository
    }

    @Provides
    @Singleton
    fun provideJackpotService(
        @RetrofitClientWithoutAuth retrofit: Retrofit,
    ): JackpotService = retrofit.create(JackpotService::class.java)
}