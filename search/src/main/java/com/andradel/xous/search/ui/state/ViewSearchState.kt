package com.andradel.xous.search.ui.state

import com.andradel.xous.search.ui.SearchItem

sealed class ViewSearchState {

    object Loading : ViewSearchState()

    object Empty : ViewSearchState()

    data class Items(
        val shows: List<SearchItem.ShowItem>,
        val people: List<SearchItem.PersonItem>
    ) : ViewSearchState()
}