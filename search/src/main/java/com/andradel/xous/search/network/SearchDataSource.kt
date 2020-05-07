package com.andradel.xous.search.network

import com.andradel.xous.commonmodels.internal.GeneralShowsResponse
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.stringresolver.StringResolver
import com.andradel.xous.core.util.safeApiCall
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val searchAPI: SearchAPI,
    private val resolver: StringResolver
) {
    // TODO: page
    suspend fun getPopularShows(): Resource<GeneralShowsResponse> = safeApiCall(resolver) {
        searchAPI.getPopularShows().toInternal()
    }

    suspend fun searchShows(query: String): Resource<GeneralShowsResponse> = safeApiCall(resolver) {
        searchAPI.searchShows(query).toInternal()
    }
}