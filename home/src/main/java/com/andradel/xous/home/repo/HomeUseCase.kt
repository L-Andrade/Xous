package com.andradel.xous.home.repo

import com.andradel.xous.commonmodels.internal.show.Show
import com.andradel.xous.commonmodels.internal.show.ShowsResponse
import com.andradel.xous.core.models.Resource
import com.andradel.xous.database.datasources.RecentlyViewedDataSource
import com.andradel.xous.home.R
import com.andradel.xous.home.network.GeneralDataSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val generalDataSource: GeneralDataSource,
    private val recentlyViewedDataSource: RecentlyViewedDataSource
) {
    fun getRecentlyViewedShows(): Flow<List<Show>> =
        recentlyViewedDataSource.getRecentlyViewedShows()

    suspend fun getAllShows(): List<Pair<Resource<ShowsResponse>, Int>> {
        return supervisorScope {
            val popular = async {
                generalDataSource.getPopular() to R.string.popular
            }
            val onTheAir = async {
                generalDataSource.getOnTheAir() to R.string.on_the_air
            }
            val topRated = async {
                generalDataSource.getTopRated() to R.string.top_rated
            }
            awaitAll(popular, onTheAir, topRated)
        }
    }
}