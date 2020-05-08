package com.andradel.xous.home.network

import com.andradel.xous.commonmodels.external.show.GeneralShowsResponseExternal
import retrofit2.http.GET

interface GeneralAPI {
    @GET("3/tv/popular")
    suspend fun getPopular(): GeneralShowsResponseExternal

    @GET("3/tv/top_rated")
    suspend fun getTopRated(): GeneralShowsResponseExternal

    @GET("3/tv/on_the_air")
    suspend fun getOnTheAir(): GeneralShowsResponseExternal
}