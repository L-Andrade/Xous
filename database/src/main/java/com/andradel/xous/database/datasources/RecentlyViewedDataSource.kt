package com.andradel.xous.database.datasources

import com.andradel.xous.common_models.internal.Show
import kotlinx.coroutines.flow.Flow

interface RecentlyViewedDataSource {
    fun getRecentlyViewedShows(): Flow<List<Show>>

    suspend fun insertRecentlyViewed(show: Show)
}