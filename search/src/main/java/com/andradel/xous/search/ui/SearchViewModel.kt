package com.andradel.xous.search.ui

import androidx.lifecycle.viewModelScope
import com.andradel.xous.commonmodels.internal.show.GeneralShowsResponse
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.viewstate.ViewStateViewModel
import com.andradel.xous.search.model.PeopleResponse
import com.andradel.xous.search.repo.SearchRepository
import com.andradel.xous.search.ui.state.SearchState
import com.andradel.xous.search.ui.state.SearchStateConverter
import com.andradel.xous.search.ui.state.ViewSearchState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    searchStateConverter: SearchStateConverter
) : ViewStateViewModel<SearchState, ViewSearchState>(
    initialData = SearchState.Loading(),
    stateConverter = searchStateConverter
) {
    private var searchJob: Job? = null
        set(value) {
            field?.cancel()
            field = value
        }
    private var initJob: Job? = null
        set(value) {
            field?.cancel()
            field = value
        }

    var query: String = ""
        set(value) {
            if (value == field) return
            field = value
            if (value.isBlank()) {
                setState(SearchState.Items(currentState))
                return
            }
            setState(SearchState.Loading(currentState))
            searchJob = viewModelScope.launch {
                initJob?.join()
                when (val resource = repository.searchShows(value)) {
                    is Resource.Success -> setState(
                        SearchState.Items(currentState, resource.data.items)
                    )
                    is Resource.Error -> setState(
                        SearchState.Empty(currentState),
                        resource.error.message
                    )
                }
            }
        }

    init {
        runWhen<SearchState.Loading> {
            initJob = repository.getPopular().onEach { resource ->
                when (resource) {
                    is Resource.Success -> when (val data = resource.data) {
                        is GeneralShowsResponse -> setState(
                            SearchState.Items(
                                popularShows = data.items,
                                popularPeople = currentState.popularPeople
                            )
                        )
                        is PeopleResponse -> setState(
                            SearchState.Items(
                                popularShows = currentState.popularShows,
                                popularPeople = data.results
                            )
                        )
                    }
                    is Resource.Error -> setState(
                        SearchState.Empty(currentState),
                        resource.error.message
                    )
                }
            }.launchIn(viewModelScope)
        }
    }
}