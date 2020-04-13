package com.andradel.xous.showprofile.model

import com.andradel.xous.common_models.external.GeneralShowsResponseExternal
import com.andradel.xous.common_models.orFalse
import com.andradel.xous.common_models.orZero
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FullShowExternal(
    @SerialName("id") val id: Int?,
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
    @SerialName("vote_average") val rating: Float?,
    @SerialName("similar") val similarShows: GeneralShowsResponseExternal,
    @SerialName("seasons") val seasons: List<SeasonExternal>?
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
            rating = rating.orZero(),
            similarShows = similarShows.toInternal(),
            seasons = seasons.orEmpty().map { it.toInternal() }
        )
}

@Serializable
data class CreatorExternal(
    @SerialName("id") val id: Int?,
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

@Serializable
data class SeasonExternal(
    @SerialName("id") val id: Int?,
    @SerialName("season_number") val number: Int?,
    @SerialName("name") val name: String?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("episode_count") val numberOfEpisodes: Int?,
    @SerialName("overview") val overview: String?
) {
    fun toInternal(): Season = Season(
        id = id.orZero(),
        number = number.orZero(),
        name = name.orEmpty(),
        posterPath = posterPath.orEmpty(),
        numberOfEpisodes = numberOfEpisodes.orZero(),
        overview = overview.orEmpty()
    )
}