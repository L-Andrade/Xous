package com.andradel.xous.showprofile.ui.season

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.commonmodels.internal.show.Show
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.util.LiveEvent
import com.andradel.xous.showprofile.model.Episode
import com.andradel.xous.showprofile.model.SeasonDetails
import com.andradel.xous.showprofile.repo.ShowProfileUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SeasonViewModel @Inject constructor(
    private val useCase: ShowProfileUseCase
) : ViewModel() {

    private val _details = MutableLiveData<SeasonDetails>()
    val details: LiveData<SeasonDetails>
        get() = _details

    private val _message = LiveEvent<Int>()
    val message: LiveData<Int>
        get() = _message

    fun getDetails(show: Show, season: Season) {
        if (_details.value != null) return
        viewModelScope.launch {
            when (val details = useCase.getSeasonDetails(show, season)) {
                is Resource.Success -> _details.value = details.data
                is Resource.Error -> _message.value = details.error.message
            }
        }
    }

    fun expandEpisode(episode: Episode) {
        val seasonDetails = _details.value ?: return
        _details.value = seasonDetails.copy(episodes = seasonDetails.episodes.map {
            if (it.id == episode.id) it.invertExpanded() else it
        })
    }
}
