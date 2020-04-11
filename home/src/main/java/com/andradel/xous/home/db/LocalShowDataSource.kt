package com.andradel.xous.home.db

import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.database.daos.ShowsDao
import com.andradel.xous.database.models.ShowType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalShowDataSource @Inject constructor(
    private val showsDao: ShowsDao
) {

    fun getRecentlyViewedShows(): Flow<List<Show>> =
        showsDao.getShowsByType(ShowType.RECENTLY_VIEWED).map { domainList ->
            domainList.map { it.toInternal() }
        }

}