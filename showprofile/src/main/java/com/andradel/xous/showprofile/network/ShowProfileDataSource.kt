package com.andradel.xous.showprofile.network

import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.stringresolver.StringResolver
import com.andradel.xous.core.util.safeApiCall
import com.andradel.xous.showprofile.model.FullShow
import com.andradel.xous.showprofile.model.SeasonDetails
import javax.inject.Inject

class ShowProfileDataSource @Inject constructor(
    private val showProfileAPI: ShowProfileAPI,
    private val stringResolver: StringResolver
) {
    suspend fun getShow(showId: Int): Resource<FullShow> = safeApiCall(stringResolver) {
        showProfileAPI.getShow(showId).toInternal()
    }

    suspend fun getSeason(showId: Int, seasonNumber: Int): Resource<SeasonDetails> =
        safeApiCall(stringResolver) { showProfileAPI.getSeason(showId, seasonNumber).toInternal() }
}