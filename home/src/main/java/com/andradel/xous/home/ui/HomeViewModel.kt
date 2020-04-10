package com.andradel.xous.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.stringresolver.StringResolver
import com.andradel.xous.core.util.LiveEvent
import com.andradel.xous.home.repo.HomeRepository
import com.andradel.xous.home.ui.model.HomeState
import com.andradel.xous.home.ui.model.ShowItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val stringResolver: StringResolver
) : ViewModel() {

    private val _state = MutableLiveData<HomeState>(HomeState.Loading)
    val state: LiveData<HomeState>
        get() = _state

    private val _message = LiveEvent<String>()
    val message: LiveData<String>
        get() = _message

    init {
        getAllShows()
    }

    private val currentItems
        get() = (_state.value as? HomeState.ShowLists)?.shows.orEmpty()

    fun getAllShows() {
        _state.value = HomeState.Loading
        repository.getAllShows().onEach {
            val (resource, title) = it
            when (resource) {
                is Resource.Success -> {
                    val items = resource.data.items.map { show ->
                        ShowItem.Item(show)
                    }
                    addItemsToState(listOf(ShowItem.Header(title)) + items)
                }
                is Resource.Error -> _message.value = resource.error.resolve(stringResolver)
            }
        }.onCompletion {
            if (currentItems.isEmpty()) _state.value = HomeState.Empty
        }.launchIn(viewModelScope)
    }

    private fun addItemsToState(items: List<ShowItem>) {
        _state.value = HomeState.ShowLists(currentItems + items)
    }
}
