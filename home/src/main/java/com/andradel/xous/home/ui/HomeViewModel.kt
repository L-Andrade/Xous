package com.andradel.xous.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.util.LiveEvent
import com.andradel.xous.home.repo.HomeRepository
import com.andradel.xous.home.ui.model.ShowItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _shows = MutableLiveData<List<ShowItem>>()
    val shows: LiveData<List<ShowItem>>
        get() = _shows

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _showEmpty = MutableLiveData<Boolean>()
    val showEmpty: LiveData<Boolean>
        get() = _showEmpty

    private val _message = LiveEvent<String>()
    val message: LiveData<String>
        get() = _message

    val recentlyViewed: LiveData<List<Show>> = repository.getRecentlyViewedShows().onEach {
        if (it.isNotEmpty() && !currentItems.contains(ShowItem.RecentlyViewedList))
            addItemsToState(listOf(ShowItem.RecentlyViewedList), true)
    }.asLiveData()

    init {
        getAllShows()
    }

    private val currentItems
        get() = _shows.value.orEmpty()

    fun getAllShows() {
        _loading.value = true
        repository.getAllShows().onEach {
            val (resource, title) = it
            when (resource) {
                is Resource.Success -> {
                    val items = resource.data.items.map { show -> ShowItem.Item(title, show) }
                    addItemsToState(listOf(ShowItem.Header(title)) + items, false)
                }
                is Resource.Error -> _message.value = resource.error.message
            }
        }.onCompletion {
            _loading.value = false
            _showEmpty.value = currentItems.size <= 1
        }.launchIn(viewModelScope)
    }

    private fun addItemsToState(items: List<ShowItem>, startOfItemList: Boolean) {
        _shows.value = when {
            startOfItemList -> items + currentItems
            else -> currentItems + items
        }
    }
}
