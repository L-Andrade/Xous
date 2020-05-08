package com.andradel.xous.core.viewstate

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andradel.xous.core.coroutine.BehaviorSubjectChannel
import com.andradel.xous.core.util.LiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class ViewStateViewModel<State : Any, ViewState : Any>(
    initialData: State,
    stateConverter: StateConverter<State, ViewState>,
    private val _state: BehaviorSubjectChannel<State> = BehaviorSubjectChannel(initialData)
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()

    private val _message = LiveEvent<String>()
    val message: LiveData<String>
        get() = _message

    init {
        flowState
            .map { stateConverter.convertToViewState(it) }
            .distinctUntilChanged()
            .flowOn(Dispatchers.Default)
            .onEach {
                _viewState.value = it
            }.launchIn(viewModelScope)
    }

    val viewState: LiveData<ViewState>
        get() = _viewState

    protected val currentState: State
        get() = _state.value

    protected val flowState: Flow<State>
        get() = _state.asFlow()

    protected fun setState(state: State, message: String? = null) {
        if (!_state.isClosedForSend) {
            _state.offer(state)
        }
        if (message != null) _message.value = message
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        _state.close()
    }

    protected inline fun <reified T : State> runWhen(block: (T) -> Unit) {
        val state = currentState
        if (state !is T) {
            val message =
                "${this::class.java.name} wanted ${T::class.java.simpleName} but was ${state::class.java.simpleName}"
            throw IllegalStateException(message)
        }
        block(state)
    }

    protected inline fun <reified T : State> launchWhen(
        context: CoroutineContext = EmptyCoroutineContext,
        crossinline block: suspend (T) -> Unit
    ): Job {
        val state = currentState
        if (state !is T) {
            val message =
                "${this::class.java.name} wanted ${T::class.java.simpleName} but was ${state::class.java.simpleName}"
            throw IllegalStateException(message)
        }
        return viewModelScope.launch(context + Dispatchers.Main) {
            block(state)
        }
    }
}