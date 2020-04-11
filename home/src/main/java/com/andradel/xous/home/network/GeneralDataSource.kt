package com.andradel.xous.home.network

import com.andradel.xous.common_models.internal.GeneralShowsResponse
import com.andradel.xous.core.models.NetworkError
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.models.Resource.Companion.failed
import com.andradel.xous.core.models.Resource.Companion.success
import com.andradel.xous.core.stringresolver.StringResolver
import com.andradel.xous.core.util.safeApiCall
import javax.inject.Inject

class GeneralDataSource @Inject constructor(
    private val generalAPI: GeneralAPI,
    private val stringResolver: StringResolver
) {
    suspend fun getPopular(): Resource<GeneralShowsResponse> = safeApiCall(stringResolver) {
        success(generalAPI.getPopular().toInternal())
    }

    suspend fun getOnTheAir(): Resource<GeneralShowsResponse> = safeApiCall(stringResolver) {
        success(generalAPI.getOnTheAir().toInternal())
    }

    suspend fun getTopRated(): Resource<GeneralShowsResponse> = safeApiCall(stringResolver) {
        success(generalAPI.getTopRated().toInternal())
    }
}
