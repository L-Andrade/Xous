package com.andradel.xous.showprofile.model

import com.andradel.xous.common_models.orFalse
import com.andradel.xous.common_models.orZero
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FullShowExternal(
    @SerialName("id") val id: Long?,
    @SerialName("name") val name: String?,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("created_by") val createdBy: List<CreatorExternal>?,
    @SerialName("first_air_date") val firstAired: String?,
    @SerialName("last_air_date") val lastAired: String?,
    @SerialName("in_production") val inProduction: Boolean?,
    @SerialName("number_of_episodes") val numberOfEpisodes: Int?,
    @SerialName("number_of_seasons") val numberOfSeasons: Int?,
    @SerialName("overview") val overview: String?,
    @SerialName("status") val status: String?,
    @SerialName("type") val type: String?,
    @SerialName("vote_average") val rating: Float?
) {
    fun toInternal(): FullShow =
        FullShow(
            id = id.orZero(),
            name = name.orEmpty(),
            backdropPath = backdropPath,
            posterPath = posterPath,
            createdBy = createdBy.orEmpty().map { it.toInternal() },
            firstAired = firstAired.orEmpty(),
            lastAired = lastAired.orEmpty(),
            inProduction = inProduction.orFalse(),
            numberOfEpisodes = numberOfEpisodes.orZero(),
            numberOfSeasons = numberOfSeasons.orZero(),
            overview = overview.orEmpty(),
            status = status.orEmpty(),
            type = type.orEmpty(),
            rating = rating.orZero()
        )
}

@Serializable
data class CreatorExternal(
    @SerialName("id") val id: Long?,
    @SerialName("name") val name: String?,
    @SerialName("profile_path") val profilePath: String?
) {
    fun toInternal(): Creator =
        Creator(
            id = id.orZero(),
            name = name.orEmpty(),
            profilePath = profilePath
        )
}