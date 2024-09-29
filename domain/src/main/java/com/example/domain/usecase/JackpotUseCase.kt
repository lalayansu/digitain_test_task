package com.example.domain.usecase

import com.example.domain.base.FlowUseCase
import com.example.domain.base.Result
import com.example.domain.di.IoDispatcher
import com.example.domain.model.jackpot.CardSuit
import com.example.domain.model.jackpot.Jackpot
import com.example.domain.repository.JackpotRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JackpotUseCase @Inject constructor(
    private val jackpotRepository: JackpotRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, Jackpot?>(ioDispatcher) {
    private var lastKnownJackpot: Jackpot? = null

    override fun execute(parameters: Unit): Flow<Result<Jackpot>> = flow {
        val firstJackpot = jackpotRepository.getJackpot()

        lastKnownJackpot = firstJackpot
        emit(Result.Success(data = firstJackpot))

        while (true) {
            val newJackpot = jackpotRepository.getJackpot()

            val mergedJackpot = lastKnownJackpot?.let { oldJackpot ->
                newJackpot?.let { mergeJackpots(oldJackpot, it) }
            } ?: newJackpot

            lastKnownJackpot = mergedJackpot
            emit(Result.Success(data = mergedJackpot))

            delay(timeMillis = 5000L)
        }
    }

    private fun mergeJackpots(oldJackpot: Jackpot, newJackpot: Jackpot): Jackpot {
        val mergedCardSuitList = if (newJackpot.cardSuitList.isNullOrEmpty()) {
            oldJackpot.cardSuitList
        } else {
            newJackpot.cardSuitList.mapIndexed { index, newCardSuit ->
                val oldCardSuit = oldJackpot.cardSuitList?.getOrNull(index)
                if (newCardSuit?.current != null) {
                    mergeCardSuits(oldCardSuit, newCardSuit)
                } else {
                    oldCardSuit ?: newCardSuit
                }
            }.filterNotNull()
        }

        return Jackpot(
            digitsAfterPoint = newJackpot.digitsAfterPoint ?: oldJackpot.digitsAfterPoint,
            currency = newJackpot.currency ?: oldJackpot.currency,
            currencySymbol = newJackpot.currencySymbol ?: oldJackpot.currencySymbol,
            jackpotWidget = newJackpot.jackpotWidget ?: oldJackpot.jackpotWidget,
            cardSuitList = mergedCardSuitList
        )
    }

    private fun mergeCardSuits(oldCardSuit: CardSuit?, newCardSuit: CardSuit): CardSuit = CardSuit(
        current = newCardSuit.current ?: oldCardSuit?.current,
        previous = oldCardSuit?.current,
        largestWin = newCardSuit.largestWin ?: oldCardSuit?.largestWin,
        largestWinDate = newCardSuit.largestWinDate ?: oldCardSuit?.largestWinDate,
        largestWinUser = newCardSuit.largestWinUser ?: oldCardSuit?.largestWinUser,
        lastWin = newCardSuit.lastWin ?: oldCardSuit?.lastWin,
        lastWinDate = newCardSuit.lastWinDate ?: oldCardSuit?.lastWinDate,
        lastWinUser = newCardSuit.lastWinUser ?: oldCardSuit?.lastWinUser,
        topMonthlyWinnerList = newCardSuit.topMonthlyWinnerList
            ?: oldCardSuit?.topMonthlyWinnerList,
        topYearlyWinners = newCardSuit.topYearlyWinners ?: oldCardSuit?.topYearlyWinners,
        wins = newCardSuit.wins ?: oldCardSuit?.wins
    )
}