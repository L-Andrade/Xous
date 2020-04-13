package com.andradel.xous.common_models.external

import com.andradel.xous.common_models.internal.GeneralShowsResponse
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.common_models.orZero
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeneralShowsResponseExternal(
    @SerialName("page") val page: Int?,
    @SerialName("total_results") val totalResults: Int?,
    @SerialName("total_pages") val totalPages: Int?,
    @SerialName("results") val items: List<ShowExternal>?
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

@Serializable
data class ShowExternal(
    @SerialName("id") val id: Int?,
    @SerialName("name") val name: String?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("vote_average") val rating: Float?,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("first_air_date") val firstAired: String?,
    @SerialName("overview") val overview: String?
) {
    fun toInternal(): Show {
        return Show(
            id = id.orZero(),
            name = name.orEmpty(),
            posterPath = posterPath,
            rating = rating.orZero(),
            backdropPath = backdropPath,
            firstAired = firstAired.orEmpty(),
            overview = overview.orEmpty()
        )
    }
}