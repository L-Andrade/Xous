package com.andradel.xous.showprofile.repo

import com.andradel.xous.common_models.internal.Season
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.models.Resource
import com.andradel.xous.database.datasources.RecentlyViewedDataSource
import com.andradel.xous.showprofile.model.FullShow
import com.andradel.xous.showprofile.model.SeasonDetails
import com.andradel.xous.showprofile.network.ShowProfileDataSource
import javax.inject.Inject

class ShowProfileRepository @Inject constructor(
    private val showProfileDataSource: ShowProfileDataSource,
    private val recentlyViewedDataSource: RecentlyViewedDataSource
) {

    suspend fun getDetails(show: Show): Resource<FullShow> {
        recentlyViewedDataSource.insertRecentlyViewed(show)
        return showProfileDataSource.getShow(show.id)
    }

    suspend fun getSeasonDetails(show: Show, season: Season): Resource<SeasonDetails> {
        return showProfileDataSource.getSeason(show.id, season.number)
    }
}