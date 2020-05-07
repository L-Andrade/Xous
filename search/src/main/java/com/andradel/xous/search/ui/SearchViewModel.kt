package com.andradel.xous.search.ui

import androidx.lifecycle.viewModelScope
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.viewstate.ViewStateViewModel
import com.andradel.xous.search.network.SearchDataSource
import com.andradel.xous.search.ui.state.SearchState
import com.andradel.xous.search.ui.state.SearchStateConverter
import com.andradel.xous.search.ui.state.ViewSearchState
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchDataSource: SearchDataSource,
    searchStateConverter: SearchStateConverter
) : ViewStateViewModel<SearchState, ViewSearchState>(
    initialData = SearchState.Loading(),
    stateConverter = searchStateConverter
) {
    var query: String = ""
        set(value) {
            field = value
            viewModelScope.launch {
                when (val resource = searchDataSource.searchShows(value)) {
                    is Resource.Success -> setState(
                        SearchState.Items(
                            query = query,
                            popularShows = currentState.popularShows,
                            queriedShows = resource.data.items
                        )
                    )
                    is Resource.Error -> setState(
                        SearchState.Empty(
                            query = query,
                            popularShows = currentState.popularShows
                        ),
                        resource.error.message
                    )
                }
            }
        }

    init {
        viewModelScope.launch {
            when (val resource = searchDataSource.getPopularShows()) {
                is Resource.Success -> setState(SearchState.Items(query, resource.data.items))
                is Resource.Error -> setState(SearchState.Empty(query), resource.error.message)
            }
        }
    }
}