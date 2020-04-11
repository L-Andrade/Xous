package com.andradel.xous.showprofile.network

import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.models.Resource.Companion.success
import com.andradel.xous.core.stringresolver.StringResolver
import com.andradel.xous.core.util.safeApiCall
import com.andradel.xous.showprofile.model.FullShow
import javax.inject.Inject

class ShowProfileDataSource @Inject constructor(
    private val showProfileAPI: ShowProfileAPI,
    private val stringResolver: StringResolver
) {
    suspend fun getShow(showId: Long): Resource<FullShow> = safeApiCall(stringResolver) {
        success(showProfileAPI.getShow(showId).toInternal())
    }
}