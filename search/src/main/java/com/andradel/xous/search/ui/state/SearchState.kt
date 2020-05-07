package com.andradel.xous.search.ui.state

import com.andradel.xous.commonmodels.internal.Show

sealed class SearchState {
    abstract val query: String
    abstract val popularShows: List<Show>
    // abstract val popularPeople: List<Person>

    data class Loading(
        override val query: String = "", override val popularShows: List<Show> = emptyList()
    ) : SearchState()

    data class Empty(
        override val query: String,
        override val popularShows: List<Show> = emptyList()
    ) : SearchState()

    data class Items(
        override val query: String,
        override val popularShows: List<Show>,
        val queriedShows: List<Show>? = null
    ) : SearchState()
}