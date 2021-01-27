package com.andradel.xous.search.network

import com.andradel.xous.commonmodels.internal.show.ShowsResponse
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.util.safeApiCall
import com.andradel.xous.search.model.PeopleResponse
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val searchAPI: SearchAPI
) {
    // TODO: page
    suspend fun getPopularShows(): Resource<ShowsResponse> = safeApiCall {
        searchAPI.getPopularShows().toInternal()
    }

    suspend fun searchShows(query: String): Resource<ShowsResponse> = safeApiCall {
        searchAPI.searchShows(query).toInternal()
    }

    suspend fun getPopularPeople(): Resource<PeopleResponse> = safeApiCall {
        searchAPI.getPopularPeople().toInternal()
    }

    suspend fun searchPeople(query: String): Resource<PeopleResponse> = safeApiCall {
        searchAPI.searchPeople(query).toInternal()
    }
}