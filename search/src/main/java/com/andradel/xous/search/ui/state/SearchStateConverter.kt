package com.andradel.xous.search.ui.state

import com.andradel.xous.core.models.Schedulers
import com.andradel.xous.core.viewstate.StateConverter
import com.andradel.xous.search.ui.SearchItem
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchStateConverter @Inject constructor(
    private val schedulers: Schedulers
) : StateConverter<SearchState, ViewSearchState> {
    override suspend fun convertToViewState(state: SearchState): ViewSearchState =
        withContext(schedulers.cpu) {
            when (state) {
                is SearchState.Loading -> ViewSearchState.Loading
                is SearchState.Empty -> ViewSearchState.Empty
                is SearchState.Items -> mapItems(state)
            }
        }

    private fun mapItems(state: SearchState.Items): ViewSearchState.Items {
        val shows = (state.queriedShows ?: state.popularShows).map { SearchItem.ShowItem(it) }
        val people = (state.queriedPeople ?: state.popularPeople).map { SearchItem.PersonItem(it) }
        return ViewSearchState.Items(shows = shows, people = people)
    }
}