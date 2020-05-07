package com.andradel.xous.core.viewstate

/**
 * Maps a [State] to a [ViewState].
 * Used with [ViewStateViewModel].
 */
interface StateConverter<State : Any, ViewState : Any> {
    suspend fun convertToViewState(state: State): ViewState
}