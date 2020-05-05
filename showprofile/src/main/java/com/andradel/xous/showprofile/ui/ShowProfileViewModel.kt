package com.andradel.xous.showprofile.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andradel.xous.commonmodels.internal.BaseShow
import com.andradel.xous.commonmodels.internal.Show
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.util.LiveEvent
import com.andradel.xous.showprofile.model.FullShow
import com.andradel.xous.showprofile.repo.ShowProfileRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShowProfileViewModel @Inject constructor(
    private val repository: ShowProfileRepository
) : ViewModel() {

    private val _show = MutableLiveData<BaseShow>()
    val show: LiveData<BaseShow>
        get() = _show

    private val _message = LiveEvent<String>()
    val message: LiveData<String>
        get() = _message

    fun getDetails(show: Show) {
        if (_show.value != null) return
        _show.value = show
        viewModelScope.launch {
            when (val details = repository.getDetails(show)) {
                is Resource.Success -> _show.value = details.data
                is Resource.Error -> _message.value = details.error.message
            }
        }
    }

    val images: Array<String>
        get() = ((_show.value as? FullShow)?.allImages.orEmpty()).toTypedArray()
}
