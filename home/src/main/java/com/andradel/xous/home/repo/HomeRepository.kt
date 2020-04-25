package com.andradel.xous.home.repo

import com.andradel.xous.common_models.internal.GeneralShowsResponse
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.stringresolver.StringResolver
import com.andradel.xous.database.datasources.RecentlyViewedDataSource
import com.andradel.xous.home.R
import com.andradel.xous.home.network.GeneralDataSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val generalDataSource: GeneralDataSource,
    private val recentlyViewedDataSource: RecentlyViewedDataSource,
    private val stringResolver: StringResolver
) {
    fun getRecentlyViewedShows(): Flow<List<Show>> =
        recentlyViewedDataSource.getRecentlyViewedShows()

    fun getAllShows(): Flow<Pair<Resource<GeneralShowsResponse>, String>> = flow {
        supervisorScope {
            val popular = async {
                generalDataSource.getPopular() to stringResolver[R.string.popular]
            }
            val onTheAir = async {
                generalDataSource.getOnTheAir() to stringResolver[R.string.on_the_air]
            }
            val topRated = async {
                generalDataSource.getTopRated() to stringResolver[R.string.top_rated]
            }
            awaitAll(popular, onTheAir, topRated).forEach { emit(it) }
        }
    }
}