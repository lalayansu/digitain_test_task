package com.example.presenter.ui.base

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class Reducer<State : ViewState, Event : ViewEvent, Effect : ViewEffect>(initialVal: State) {

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialVal)
    val state: StateFlow<State>
        get() = _state.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event>
        get() = _event.asSharedFlow()

    private val _effects = Channel<Effect>(capacity = Channel.CONFLATED)
    val effect = _effects.receiveAsFlow()

    fun sendEffect(effect: Effect) {
        _effects.trySend(effect)
    }

    fun sendEvent(event: Event) = reduce(_state.value, event)

    fun setState(newState: State) {
        _state.tryEmit(newState)
    }

    abstract fun reduce(oldState: State, event: Event)
}

interface ViewState

interface ViewEvent

interface ViewEffect