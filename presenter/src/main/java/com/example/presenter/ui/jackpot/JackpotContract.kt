package com.example.presenter.ui.jackpot

import androidx.compose.runtime.Immutable
import com.example.presenter.model.jackpot.JackpotUiModel
import com.example.presenter.ui.base.ViewEffect
import com.example.presenter.ui.base.ViewEvent
import com.example.presenter.ui.base.ViewState

class JackpotContract {
    @Immutable
    sealed class JackpotViewEvent : ViewEvent {
        data class IsLoading(val isLoading: Boolean) : JackpotViewEvent()
        data class OnError(val errorMessage: String) : JackpotViewEvent()
        data class UpdateJackpot(val jackpotUiModel: JackpotUiModel?) : JackpotViewEvent()
        data object OnJackpotClick : JackpotViewEvent()
    }

    @Immutable
    sealed class JackpotViewEffect : ViewEffect {
        data object ShowError : JackpotViewEffect()
        data object NavigateToJackpotDetails : JackpotViewEffect()
    }

    @Immutable
    data class JackpotViewState(
        val isLoading: Boolean,
        val errorMessage: String,
        val jackpotUiModel: JackpotUiModel?,
    ) : ViewState {
        companion object {
            fun initial() = JackpotViewState(
                isLoading = false,
                errorMessage = "",
                jackpotUiModel = null
            )
        }
    }
}