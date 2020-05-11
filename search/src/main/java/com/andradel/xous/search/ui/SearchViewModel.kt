package com.andradel.xous.search.ui

import androidx.lifecycle.viewModelScope
import com.andradel.xous.commonmodels.internal.show.ShowsResponse
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
            search()
        }

    init {
        if (initJob == null || initJob?.isCancelled == true) {
            initJob = repository.getPopular().onEach { resource ->
                when (resource) {
                    is Resource.Success -> when (val data = resource.data) {
                        is ShowsResponse -> setState(
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

    private fun search() {
        // For now, we're querying all sections concurrently.
        // In the future, I'd like to query only the visible fragment!

        setState(SearchState.Loading(currentState))

        searchJob = repository.search(query).onEach { resource ->
            initJob?.join()

            when (resource) {
                is Resource.Success -> when (val data = resource.data) {
                    is PeopleResponse -> setState(
                        SearchState.Items(
                            currentState,
                            queriedPeople = data.results
                        )
                    )
                    is ShowsResponse -> setState(
                        SearchState.Items(
                            currentState,
                            queriedShows = data.items
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