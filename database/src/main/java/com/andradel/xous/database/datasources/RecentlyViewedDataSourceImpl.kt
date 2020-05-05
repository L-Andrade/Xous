package com.andradel.xous.database.datasources

import com.andradel.xous.commonmodels.internal.Show
import com.andradel.xous.database.daos.ShowsDao
import com.andradel.xous.database.models.ShowType
import com.andradel.xous.database.models.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class RecentlyViewedDataSourceImpl @Inject constructor(
    private val showsDao: ShowsDao
) : RecentlyViewedDataSource {

    override fun getRecentlyViewedShows(): Flow<List<Show>> =
        showsDao.getShowsByType(ShowType.RECENTLY_VIEWED).map { domainList ->
            domainList.map { it.toInternal() }
        }

    override suspend fun insertRecentlyViewed(show: Show) {
        showsDao.insert(show.toDomain(ShowType.RECENTLY_VIEWED))
    }
}