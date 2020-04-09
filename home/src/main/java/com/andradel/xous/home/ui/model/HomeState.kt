package com.andradel.xous.home.ui.model

sealed class HomeState {
    object Loading : HomeState()
    object Empty : HomeState()
    data class ShowLists(val shows: List<ShowItem>) : HomeState()
}