package com.andradel.xous.search.network

import com.andradel.xous.commonmodels.external.GeneralShowsResponseExternal
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {
    // TODO: Page
    @GET("3/tv/popular")
    suspend fun getPopularShows(): GeneralShowsResponseExternal

    @GET("3/search/tv")
    suspend fun searchShows(@Query("query") query: String): GeneralShowsResponseExternal

    @GET("3/search/person")
    suspend fun searchPeople(@Query("query") query: String)
}