package com.andradel.xous.search.ui.state

import com.andradel.xous.core.models.Schedulers
import com.andradel.xous.core.viewstate.StateConverter
import javax.inject.Inject

class SearchStateConverter @Inject constructor(
    private val schedulers: Schedulers
) : StateConverter<SearchState, ViewSearchState> {
    override suspend fun convertToViewState(state: SearchState): ViewSearchState {
        TODO("Not yet implemented")
    }
}