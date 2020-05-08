package com.andradel.xous.search.network

import com.andradel.xous.commonmodels.internal.show.ShowsResponse
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.stringresolver.StringResolver
import com.andradel.xous.core.util.safeApiCall
import com.andradel.xous.search.model.PeopleResponse
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val searchAPI: SearchAPI,
    private val resolver: StringResolver
) {
    // TODO: page
    suspend fun getPopularShows(): Resource<ShowsResponse> = safeApiCall(resolver) {
        searchAPI.getPopularShows().toInternal()
    }

    suspend fun searchShows(query: String): Resource<ShowsResponse> = safeApiCall(resolver) {
        searchAPI.searchShows(query).toInternal()
    }

    suspend fun getPopularPeople(): Resource<PeopleResponse> = safeApiCall(resolver) {
        searchAPI.getPopularPeople().toInternal()
    }

    suspend fun searchPeople(query: String): Resource<PeopleResponse> = safeApiCall(resolver) {
        searchAPI.searchPeople(query).toInternal()
    }
}