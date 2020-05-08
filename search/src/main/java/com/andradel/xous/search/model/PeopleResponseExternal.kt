package com.andradel.xous.search.model

import com.andradel.xous.commonmodels.orZero
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeopleResponseExternal(
    @SerialName("page") val page: Int? = null,
    @SerialName("total_results") val totalResults: Int? = null,
    @SerialName("total_pages") val totalPages: Int? = null,
    @SerialName("results") val results: List<RegularPersonExternal>? = null
) {
    fun toInternal(): PeopleResponse = PeopleResponse(
        page = page.orZero(),
        totalResults = totalResults.orZero(),
        totalPages = totalPages.orZero(),
        results = results.orEmpty().map { it.toInternal() }
    )
}