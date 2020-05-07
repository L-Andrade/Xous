package com.andradel.xous.search.ui.state

import com.andradel.xous.search.ui.SearchItem

sealed class ViewSearchState {

    object Loading : ViewSearchState()

    object Empty : ViewSearchState()

    data class Items(val items: List<SearchItem>) : ViewSearchState()
}