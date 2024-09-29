package com.example.presenter.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State : ViewState, Event : ViewEvent, Effect : ViewEffect> :
    ViewModel() {
    abstract val state: StateFlow<State>

    abstract val event: SharedFlow<Event>

    abstract val effect: Flow<Effect>
}