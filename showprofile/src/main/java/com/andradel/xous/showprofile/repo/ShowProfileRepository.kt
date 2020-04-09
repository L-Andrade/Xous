package com.andradel.xous.showprofile.repo

import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.showprofile.network.ShowProfileDataSource
import javax.inject.Inject

class ShowProfileRepository @Inject constructor(
    private val showProfileDataSource: ShowProfileDataSource
) {

    suspend fun getDetails(show: Show) {
        // Add show to DB

        // Retrieve full details
        showProfileDataSource.getShow(show.id)
    }
}