package com.example.presenter.ui.jackpot

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.viewModelScope
import com.example.domain.base.Result
import com.example.domain.di.IoDispatcher
import com.example.domain.usecase.JackpotUseCase
import com.example.presenter.model.jackpot.toUiModel
import com.example.presenter.ui.base.BaseViewModel
import com.example.presenter.ui.base.Reducer
import com.example.presenter.ui.jackpot.JackpotContract.JackpotViewEffect
import com.example.presenter.ui.jackpot.JackpotContract.JackpotViewEffect.NavigateToJackpotDetails
import com.example.presenter.ui.jackpot.JackpotContract.JackpotViewEffect.ShowError
import com.example.presenter.ui.jackpot.JackpotContract.JackpotViewEvent
import com.example.presenter.ui.jackpot.JackpotContract.JackpotViewEvent.IsLoading
import com.example.presenter.ui.jackpot.JackpotContract.JackpotViewEvent.OnError
import com.example.presenter.ui.jackpot.JackpotContract.JackpotViewEvent.OnJackpotClick
import com.example.presenter.ui.jackpot.JackpotContract.JackpotViewEvent.UpdateJackpot
import com.example.presenter.ui.jackpot.JackpotContract.JackpotViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JackpotViewModel @Inject constructor(
    private val jackpotUseCase: JackpotUseCase,
    @IoDispatcher private val dispatcherIO: CoroutineDispatcher
) : BaseViewModel<JackpotViewState, JackpotViewEvent, JackpotViewEffect>(), LifecycleEventObserver {
    override val state: StateFlow<JackpotViewState>
        get() = reducer.state

    override val event: SharedFlow<JackpotViewEvent>
        get() = reducer.event

    override val effect: Flow<JackpotViewEffect>
        get() = reducer.effect

    private val reducer = JackpotReducer(JackpotViewState.initial())
    private var periodicUpdateJob: Job? = null

    init {
        ProcessLifecycleOwner.get().lifecycle.addObserver(observer = this)
    }

    fun onJackpotClick() = sendEvent(event = OnJackpotClick)

    fun dismissErrorDialog() {
        periodicUpdateJob = null
        startPeriodicJackpotUpdates()
    }

    private fun startPeriodicJackpotUpdates() {
        if (periodicUpdateJob == null || periodicUpdateJob?.isCancelled == true) {
            periodicUpdateJob = viewModelScope.launch(dispatcherIO) {
                sendEvent(event = IsLoading(isLoading = true))
                jackpotUseCase(Unit)
                    .onEach { result ->
                        when (result) {
                            is Result.Success ->
                                sendEvent(UpdateJackpot(jackpotUiModel = result.data?.toUiModel()))

                            is Result.Error ->
                                sendEvent(OnError(errorMessage = result.error.commonErrorMessage))

                            Result.UncheckedError ->
                                sendEvent(OnError(errorMessage = "Something went wrong"))
                        }
                        sendEvent(event = IsLoading(isLoading = false))
                    }
                    .launchIn(viewModelScope)
            }
        }
    }

    private fun stopPeriodicJackpotUpdates() {
        periodicUpdateJob?.cancel()
    }

    private fun sendEvent(event: JackpotViewEvent) = reducer.sendEvent(event = event)

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) = when (event) {
        Lifecycle.Event.ON_START -> startPeriodicJackpotUpdates()
        Lifecycle.Event.ON_STOP -> stopPeriodicJackpotUpdates()
        else -> Unit
    }

    override fun onCleared() {
        super.onCleared()
        ProcessLifecycleOwner.get().lifecycle.removeObserver(observer = this)
    }

    private class JackpotReducer(initial: JackpotViewState) :
        Reducer<JackpotViewState, JackpotViewEvent, JackpotViewEffect>(initial) {
        override fun reduce(oldState: JackpotViewState, event: JackpotViewEvent) = when (event) {
            is IsLoading -> setState(oldState.copy(isLoading = event.isLoading))
            is UpdateJackpot -> setState(oldState.copy(jackpotUiModel = event.jackpotUiModel))
            is OnError -> {
                sendEffect(effect = ShowError)
                setState(oldState.copy(errorMessage = event.errorMessage))
            }

            OnJackpotClick -> sendEffect(effect = NavigateToJackpotDetails)
        }
    }
}