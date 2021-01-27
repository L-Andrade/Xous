package com.andradel.xous.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.andradel.xous.commonmodels.internal.show.Show
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.util.LiveEvent
import com.andradel.xous.home.repo.HomeUseCase
import com.andradel.xous.home.ui.model.ShowItem
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
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

    private val _message = LiveEvent<Int>()
    val message: LiveData<Int>
        get() = _message

    val recentlyViewed: LiveData<List<Show>> = useCase.getRecentlyViewedShows().onEach {
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
        viewModelScope.launch {
            useCase.getAllShows().forEach {
                val (resource, title) = it
                when (resource) {
                    is Resource.Success -> {
                        val items = resource.data.items.map { show -> ShowItem.Item(title, show) }
                        addItemsToState(listOf(ShowItem.Header(title)) + items, false)
                    }
                    is Resource.Error -> _message.value = resource.error.message
                }
            }

            _loading.value = false
            _showEmpty.value = currentItems.size <= 1
        }
    }

    private fun addItemsToState(items: List<ShowItem>, startOfItemList: Boolean) {
        _shows.value = when {
            startOfItemList -> items + currentItems
            else -> currentItems + items
        }
    }
}
