package com.andradel.xous.showprofile.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andradel.xous.commonmodels.internal.show.Show
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.util.LiveEvent
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.FullShow
import com.andradel.xous.showprofile.repo.ShowProfileRepository
import com.andradel.xous.showprofile.ui.adapter.ProfileItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShowProfileViewModel @Inject constructor(
    private val repository: ShowProfileRepository
) : ViewModel() {

    private val _show = MutableLiveData<ProfileState>()
    val show: LiveData<ProfileState>
        get() = _show

    private val _message = LiveEvent<String>()
    val message: LiveData<String>
        get() = _message

    fun getDetails(show: Show) {
        if (_show.value != null) return
        _show.value = ProfileState(listOf(ProfileItem.Overview(show)), show)
        viewModelScope.launch {
            when (val details = repository.getDetails(show)) {
                is Resource.Success -> _show.value = mapDetails(details.data)
                is Resource.Error -> _message.value = details.error.message
            }
        }
    }

    private fun mapDetails(data: FullShow): ProfileState {
        return ProfileState(
            items = listOf(
                ProfileItem.Overview(data),
                ProfileItem.Content.People(R.string.creators_and_crew, data.crew),
                ProfileItem.Content.People(R.string.cast, data.cast),
                ProfileItem.Content.Seasons(R.string.seasons, data.seasons),
                ProfileItem.Content.SimilarShows(R.string.similar_shows, data.similarShows.items)
            ),
            show = data
        )
    }

    val images: Array<String>
        get() = ((_show.value?.show as? FullShow)?.allImages.orEmpty()).toTypedArray()
}
