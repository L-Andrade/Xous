package com.andradel.xous.showprofile.ui.season

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andradel.xous.common_models.internal.Season
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.util.LiveEvent
import com.andradel.xous.showprofile.model.SeasonDetails
import com.andradel.xous.showprofile.repo.ShowProfileRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SeasonViewModel @Inject constructor(
    private val repository: ShowProfileRepository
) : ViewModel() {

    private val _details = MutableLiveData<SeasonDetails>()
    val details: LiveData<SeasonDetails>
        get() = _details

    private val _message = LiveEvent<String>()
    val message: LiveData<String>
        get() = _message

    fun getDetails(show: Show, season: Season) {
        if (_details.value != null) return
        viewModelScope.launch {
            when (val details = repository.getSeasonDetails(show, season)) {
                is Resource.Success -> _details.value = details.data
                is Resource.Error -> _message.value = details.error.message
            }
        }
    }
}
