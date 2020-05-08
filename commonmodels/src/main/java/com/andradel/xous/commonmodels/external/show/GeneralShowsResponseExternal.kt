package com.andradel.xous.commonmodels.external.show

import com.andradel.xous.commonmodels.internal.show.GeneralShowsResponse
import com.andradel.xous.commonmodels.orZero
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeneralShowsResponseExternal(
    @SerialName("page") val page: Int? = null,
    @SerialName("total_results") val totalResults: Int? = null,
    @SerialName("total_pages") val totalPages: Int? = null,
    @SerialName("results") val items: List<ShowExternal>? = null
) {
    fun toInternal(): GeneralShowsResponse {
        return GeneralShowsResponse(
            page = page.orZero(),
            totalPages = totalPages.orZero(),
            totalResults = totalResults.orZero(),
            items = items.orEmpty().map { it.toInternal() }
        )
    }
}