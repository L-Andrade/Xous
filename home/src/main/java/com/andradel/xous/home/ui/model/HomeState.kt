package com.andradel.xous.home.ui.model

sealed class HomeState {
    object Loading : HomeState()
    data class Empty(val shows: List<ShowItem>) : HomeState()
    data class ShowLists(val shows: List<ShowItem>) : HomeState()
}