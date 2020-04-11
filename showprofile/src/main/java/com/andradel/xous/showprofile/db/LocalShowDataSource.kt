package com.andradel.xous.showprofile.db

import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.database.daos.ShowsDao
import com.andradel.xous.database.models.ShowType
import com.andradel.xous.database.models.toDomain
import javax.inject.Inject

class LocalShowDataSource @Inject constructor(
    private val showsDao: ShowsDao
) {
    suspend fun insertRecentlyViewed(show: Show) {
        showsDao.insert(show.toDomain(ShowType.RECENTLY_VIEWED))
    }
}