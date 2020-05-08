package com.andradel.xous.search.ui.state

import com.andradel.xous.commonmodels.internal.person.RegularPerson
import com.andradel.xous.commonmodels.internal.show.Show

sealed class SearchState {
    abstract val popularShows: List<Show>
    abstract val popularPeople: List<RegularPerson>

    data class Loading(
        override val popularShows: List<Show>,
        override val popularPeople: List<RegularPerson>
    ) : SearchState() {
        constructor() : this(emptyList(), emptyList())
        constructor(state: SearchState) : this(state.popularShows, state.popularPeople)
    }

    data class Empty(
        override val popularShows: List<Show>,
        override val popularPeople: List<RegularPerson>
    ) : SearchState() {
        constructor(state: SearchState) : this(state.popularShows, state.popularPeople)
    }

    data class Items(
        override val popularShows: List<Show>,
        override val popularPeople: List<RegularPerson>,
        val queriedShows: List<Show>? = null,
        val queriedPeople: List<RegularPerson>? = null
    ) : SearchState() {
        constructor(
            state: SearchState,
            queriedShows: List<Show>? = null,
            queriedPeople: List<RegularPerson>? = null
        ) : this(state.popularShows, state.popularPeople, queriedShows, queriedPeople)
    }
}