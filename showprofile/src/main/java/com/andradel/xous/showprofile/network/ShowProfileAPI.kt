package com.andradel.xous.showprofile.network

import com.andradel.xous.showprofile.model.FullShowExternal
import retrofit2.http.GET
import retrofit2.http.Path

interface ShowProfileAPI {
    @GET("3/tv/{tv_id}?append_to_response=images,similar,credits")
    suspend fun getShow(@Path("tv_id") showId: Int): FullShowExternal
}