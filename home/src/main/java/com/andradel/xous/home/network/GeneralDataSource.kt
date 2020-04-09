package com.andradel.xous.home.network

import com.andradel.xous.common_models.internal.GeneralShowsResponse
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.models.Resource.Companion.failed
import com.andradel.xous.core.models.Resource.Companion.success
import com.andradel.xous.core.util.safeApiCall
import javax.inject.Inject

class GeneralDataSource @Inject constructor(
    private val generalAPI: GeneralAPI
) {
    suspend fun getPopular(): Resource<GeneralShowsResponse> = safeApiCall({
        success(generalAPI.getPopular().toInternal())
    }, {
        failed(it)
    })

    suspend fun getOnTheAir(): Resource<GeneralShowsResponse> = safeApiCall({
        success(generalAPI.getOnTheAir().toInternal())
    }, {
        failed(it)
    })

    suspend fun getTopRated(): Resource<GeneralShowsResponse> = safeApiCall({
        success(generalAPI.getTopRated().toInternal())
    }, {
        failed(it)
    })
}
