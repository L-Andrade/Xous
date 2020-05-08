package com.andradel.xous.showprofile.model

import com.andradel.xous.commonmodels.ImageSize
import com.andradel.xous.commonmodels.external.show.GeneralShowsResponseExternal
import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.commonmodels.internal.person.CastMember
import com.andradel.xous.commonmodels.internal.person.CrewMember
import com.andradel.xous.commonmodels.nullIfBlank
import com.andradel.xous.commonmodels.orFalse
import com.andradel.xous.commonmodels.orZero
import com.andradel.xous.commonmodels.toImagePath
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FullShowExternal(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("created_by") val createdBy: List<CreatorExternal>? = null,
    @SerialName("first_air_date") val firstAired: String? = null,
    @SerialName("last_air_date") val lastAired: String? = null,
    @SerialName("in_production") val inProduction: Boolean? = null,
    @SerialName("number_of_episodes") val numberOfEpisodes: Int? = null,
    @SerialName("number_of_seasons") val numberOfSeasons: Int? = null,
    @SerialName("overview") val overview: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("vote_average") val rating: Float? = null,
    @SerialName("similar") val similarShows: GeneralShowsResponseExternal =
        GeneralShowsResponseExternal(),
    @SerialName("seasons") val seasons: List<SeasonExternal>? = null,
    @SerialName("images") val images: ImagesExternal? = null,
    @SerialName("credits") val credits: CreditsExternal? = null
) {
    fun toInternal(): FullShow =
        FullShow(
            id = id.orZero(),
            name = name.orEmpty(),
            backdropUrl = backdropPath.toImagePath(ImageSize.Backdrop.Medium),
            posterUrl = posterPath.toImagePath(ImageSize.Poster.Medium),
            crew = createdBy.orEmpty().map { it.toInternal() } +
                credits?.crew.orEmpty().map { it.toInternal() },
            cast = credits?.cast.orEmpty().sortedBy { it.order }.map { it.toInternal() },
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
            seasons = seasons.orEmpty().map { it.toInternal() },
            backdrops = images?.backdrops.orEmpty().mapNotNull {
                it.path.orEmpty().toImagePath(ImageSize.Backdrop.Medium)
            },
            allImages = images?.backdrops.orEmpty().mapNotNull {
                it.path.orEmpty().toImagePath()
            } + images?.posters.orEmpty().mapNotNull {
                it.path.orEmpty().toImagePath()
            }
        )
}

@Serializable
data class CreditsExternal(
    @SerialName("cast") val cast: List<CastMemberExternal>? = null,
    @SerialName("crew") val crew: List<CrewMemberExternal>? = null
)

@Serializable
data class CreatorExternal(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("profile_path") val profilePath: String? = null
) {
    fun toInternal(): CrewMember =
        CrewMember(
            id = id.orZero(),
            name = name.orEmpty(),
            job = null,
            department = null,
            profileUrl = profilePath.toImagePath(ImageSize.Profile.Medium),
            isCreator = true
        )
}

@Serializable
data class CrewMemberExternal(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("profile_path") val profilePath: String? = null,
    @SerialName("job") val job: String? = null,
    @SerialName("department") val department: String? = null
) {
    fun toInternal(): CrewMember =
        CrewMember(
            id = id.orZero(),
            name = name.orEmpty(),
            job = job.nullIfBlank(),
            department = department,
            profileUrl = profilePath.toImagePath(ImageSize.Profile.Medium),
            isCreator = false
        )
}

@Serializable
data class CastMemberExternal(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("profile_path") val profilePath: String? = null,
    @SerialName("character") val character: String? = null,
    @SerialName("order") val order: Int? = null
) {
    fun toInternal(): CastMember = CastMember(
        id = id.orZero(),
        name = name.orEmpty(),
        profileUrl = profilePath.toImagePath(ImageSize.Profile.Medium),
        character = character.orEmpty()
    )
}

@Serializable
data class SeasonExternal(
    @SerialName("id") val id: Int? = null,
    @SerialName("season_number") val number: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("episode_count") val numberOfEpisodes: Int? = null,
    @SerialName("overview") val overview: String? = null,
    @SerialName("air_date") val firstAired: String? = null
) {
    fun toInternal(): Season = Season(
        id = id.orZero(),
        number = number.orZero(),
        name = name.orEmpty(),
        posterUrl = posterPath.toImagePath(ImageSize.Poster.Medium),
        numberOfEpisodes = numberOfEpisodes.orZero(),
        overview = overview.orEmpty(),
        firstAired = firstAired.orEmpty()
    )
}

@Serializable
data class ImagesExternal(
    @SerialName("backdrops") val backdrops: List<ImageExternal>? = null,
    @SerialName("posters") val posters: List<ImageExternal>? = null
)

@Serializable
data class ImageExternal(
    @SerialName("file_path") val path: String? = null
)