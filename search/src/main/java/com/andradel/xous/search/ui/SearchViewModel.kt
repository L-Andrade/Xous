package com.andradel.xous.search.ui

import androidx.lifecycle.viewModelScope
import com.andradel.xous.commonmodels.internal.show.ShowsResponse
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.viewstate.ViewStateViewModel
import com.andradel.xous.search.model.PeopleResponse
import com.andradel.xous.search.repo.SearchUseCase
import com.andradel.xous.search.ui.state.SearchState
import com.andradel.xous.search.ui.state.SearchStateConverter
import com.andradel.xous.search.ui.state.ViewSearchState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val useCase: SearchUseCase,
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
            initJob = viewModelScope.launch {
                useCase.getPopular().forEach { resource ->
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
                }
            }
        }
    }

    private fun search() {
        // For now, we're querying all sections concurrently.
        // In the future, I'd like to query only the visible fragment!

        setState(SearchState.Loading(currentState))

        searchJob = viewModelScope.launch {
            useCase.search(query).forEach { resource ->
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
            }
        }
    }
}