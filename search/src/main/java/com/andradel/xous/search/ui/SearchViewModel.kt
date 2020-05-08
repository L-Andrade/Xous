package com.andradel.xous.search.ui

import androidx.lifecycle.viewModelScope
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.viewstate.ViewStateViewModel
import com.andradel.xous.search.network.SearchDataSource
import com.andradel.xous.search.ui.state.SearchState
import com.andradel.xous.search.ui.state.SearchStateConverter
import com.andradel.xous.search.ui.state.ViewSearchState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchDataSource: SearchDataSource,
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

    var query: String = ""
        set(value) {
            field = value
            if (value.isBlank()) {
                setState(SearchState.Items(currentState.popularShows))
                return
            }
            setState(SearchState.Loading(popularShows = currentState.popularShows))
            searchJob = viewModelScope.launch {
                when (val resource = searchDataSource.searchShows(value)) {
                    is Resource.Success -> setState(
                        SearchState.Items(
                            popularShows = currentState.popularShows,
                            queriedShows = resource.data.items
                        )
                    )
                    is Resource.Error -> setState(
                        SearchState.Empty(
                            popularShows = currentState.popularShows
                        ),
                        resource.error.message
                    )
                }
            }
        }

    init {
        searchJob = launchWhen<SearchState.Loading> {
            when (val resource = searchDataSource.getPopularShows()) {
                is Resource.Success -> setState(SearchState.Items(resource.data.items))
                is Resource.Error -> setState(SearchState.Empty(), resource.error.message)
            }
        }
    }
}