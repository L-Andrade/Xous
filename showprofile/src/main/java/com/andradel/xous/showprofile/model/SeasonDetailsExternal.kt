package com.andradel.xous.showprofile.model

import com.andradel.xous.commonmodels.ImageSize
import com.andradel.xous.commonmodels.orZero
import com.andradel.xous.commonmodels.toImagePath
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonDetailsExternal(
    @SerialName("episodes") val episodes: List<EpisodeExternal>? = null
) {
    fun toInternal(): SeasonDetails = SeasonDetails(
        episodes = episodes.orEmpty().map { it.toInternal() },
        episodeAverage = episodes.orEmpty().mapNotNull { it.rating }.average().toFloat()
    )
}

@Serializable
data class EpisodeExternal(
    @SerialName("id") val id: Int? = null,
    @SerialName("vote_average") val rating: Float? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("still_path") val stillPath: String? = null,
    @SerialName("overview") val overview: String? = null,
    @SerialName("episode_number") val number: Int? = null,
    @SerialName("guest_stars") val guestStars: List<CastMemberExternal>? = null,
    @SerialName("crew") val crew: List<CrewMemberExternal>? = null
) {
    fun toInternal(): Episode = Episode(
        id = id.orZero(),
        name = name.orEmpty(),
        overview = overview.orEmpty(),
        stillUrl = stillPath.toImagePath(ImageSize.Still.Big),
        rating = rating.orZero(),
        number = number.orZero(),
        guestStars = guestStars.orEmpty().map { it.toInternal() },
        crew = crew.orEmpty().map { it.toInternal() }
    )
}