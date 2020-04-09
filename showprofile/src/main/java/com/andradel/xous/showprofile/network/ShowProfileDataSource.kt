package com.andradel.xous.showprofile.network

import com.andradel.xous.showprofile.model.FullShow
import javax.inject.Inject

class ShowProfileDataSource @Inject constructor(
    private val showProfileAPI: ShowProfileAPI
) {
    suspend fun getShow(showId: Long): FullShow = showProfileAPI.getShow(showId).toInternal()
}