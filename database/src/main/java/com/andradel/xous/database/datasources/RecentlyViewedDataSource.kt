package com.andradel.xous.database.datasources

import com.andradel.xous.commonmodels.internal.show.Show
import kotlinx.coroutines.flow.Flow

interface RecentlyViewedDataSource {
    fun getRecentlyViewedShows(): Flow<List<Show>>

    suspend fun insertRecentlyViewed(show: Show)
}