package com.andradel.xous.home.network

import com.andradel.xous.commonmodels.internal.show.ShowsResponse
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.util.safeApiCall
import javax.inject.Inject

class GeneralDataSource @Inject constructor(
    private val generalAPI: GeneralAPI
) {
    suspend fun getPopular(): Resource<ShowsResponse> = safeApiCall {
        generalAPI.getPopular().toInternal()
    }

    suspend fun getOnTheAir(): Resource<ShowsResponse> = safeApiCall {
        generalAPI.getOnTheAir().toInternal()
    }

    suspend fun getTopRated(): Resource<ShowsResponse> = safeApiCall {
        generalAPI.getTopRated().toInternal()
    }
}
